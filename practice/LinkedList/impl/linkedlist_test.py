# Set up borrowed from https://github.com/branbick/unit-test-c-code-via-python/blob/main/examples/2_multiple_tests/sum_ints_test.py
from cffi import FFI  # type: ignore # load
from subprocess import run, PIPE
from importlib import import_module  # load
import unittest  # AddIntsTest

import random

def preprocess(file):
    return run(['gcc', '-E', '-P', '-'], input=file, stdout=PIPE, check=True,
               text=True).stdout

def load(file_name_no_ext):
    with open(f'{file_name_no_ext}.h') as header_file, \
         open(f'{file_name_no_ext}.c') as source_file:
        # Instantiate an FFI object
        ffibuilder = FFI()

        # Register all the user-defined types, variable and function
        # declarations, etc. Preprocess the header file first to "remove" the
        # #include statement which cdef can't handle.
        ffibuilder.cdef(preprocess(header_file.read()))

        # Register all the variable and function definitions, etc.
        module_name = f'{file_name_no_ext}_cffi'
        ffibuilder.set_source(module_name, source_file.read())

        # Build the registered C program
        ffibuilder.compile()

    # Import the module corresponding to the C program and return its lib
    # member. The latter's members are, effectively, the C functions.
    return import_module(module_name).lib


class LinkedListTest(unittest.TestCase):

    def setUp(self):
        self.module_lib = load('linkedlist')

    def test_create_linkedlist(self):
        head = self.module_lib.create_linkedlist()
        self.assertEqual(self.module_lib.size(head), 0)
        self.assertEqual(self.module_lib.empty(head), 1)
    
    def test_push_front(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_front(head, 23)
        self.assertEqual(self.module_lib.size(head), 1)
        self.assertEqual(self.module_lib.value_at(head, 0).value, 23)
        self.assertEqual(self.module_lib.value_at(head, 0).valid, 1)

        self.assertEqual(self.module_lib.value_at(head, 1).value, 0) # invalid
        self.assertEqual(self.module_lib.value_at(head, 1).valid, 0)

    
    def test_push_front_loop(self):
        head = self.module_lib.create_linkedlist()
        for i in range(0, 9):
            self.module_lib.push_front(head, i)
        self.assertEqual(self.module_lib.size(head), 9)
        
        for i in range(0, 9):
            self.assertEqual(self.module_lib.value_at(head, i).value, 8 - i)
    
    def test_pop_front_empty(self):
        head = self.module_lib.create_linkedlist()
        self.assertEqual(self.module_lib.pop_front(head).valid, 0)
    
    def test_pop_front(self):
        head = self.module_lib.create_linkedlist()

        self.module_lib.push_front(head, 23)
        self.module_lib.push_front(head, 3)
        self.module_lib.push_front(head, 2003)
        
        self.assertEqual(self.module_lib.pop_front(head).value, 2003)
        self.assertEqual(self.module_lib.size(head), 2)
        self.assertFalse(self.module_lib.empty(head))

        self.assertEqual(self.module_lib.pop_front(head).value, 3)
        self.assertEqual(self.module_lib.size(head), 1)
        self.assertFalse(self.module_lib.empty(head))

        self.assertEqual(self.module_lib.pop_front(head).value, 23)
        self.assertEqual(self.module_lib.size(head), 0)
        self.assertTrue(self.module_lib.empty(head))
    
    def test_pop_front_loop(self):
        head = self.module_lib.create_linkedlist()

        l = [random.randint(-20, 20) for _ in range(10)]

        for i in range(10):
            self.module_lib.push_front(head, l[i])
            self.assertEqual(self.module_lib.value_at(head, 0).value, l[i])
        
        size = 10

        for i in range(10):
            self.assertEqual(self.module_lib.pop_front(head).value, l[9-i])
            size-=1
            self.assertEqual(self.module_lib.size(head), size)
            if i != 9:
                self.assertFalse(self.module_lib.empty(head))
                self.assertEqual(self.module_lib.value_at(head, 0).value, l[9-i-1])
            else:
                self.assertTrue(self.module_lib.empty(head))

    
    def test_push_back(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_back(head, 18)
        self.assertEqual(self.module_lib.size(head), 1)
        self.assertEqual(self.module_lib.value_at(head, 0).value, 18)
    
    def test_push_back_loop(self):
        head = self.module_lib.create_linkedlist()
        
        l = [random.randint(-20, 20) for _ in range(10)]

        for i in range(10):
            self.module_lib.push_back(head, l[i])
        
        for i in range(10):
            self.assertEqual(self.module_lib.value_at(head, i).value, l[i])
    
    def test_pop_back(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_back(head, 3)
        self.module_lib.push_front(head, 23)
        self.module_lib.push_front(head, 2003)
        self.module_lib.push_back(head, 127)

        self.assertEqual(self.module_lib.pop_back(head).value, 127)
        self.assertEqual(self.module_lib.size(head), 3)
        self.assertEqual(self.module_lib.value_at(head, 2).value, 3)

        self.assertEqual(self.module_lib.pop_back(head).value, 3)
        self.assertEqual(self.module_lib.size(head), 2)
        self.assertEqual(self.module_lib.value_at(head, 1).value, 23)

        self.assertEqual(self.module_lib.pop_back(head).value, 23)
        self.assertEqual(self.module_lib.size(head), 1)
        self.assertEqual(self.module_lib.value_at(head, 0).value, 2003)

        self.assertEqual(self.module_lib.pop_back(head).value, 2003)
        self.assertTrue(self.module_lib.empty(head))

        self.assertEqual(self.module_lib.pop_back(head).valid, 0) # invalid

    def test_front(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_back(head, 3)
        self.module_lib.push_front(head, 23)
        self.module_lib.push_front(head, 2003)
        self.module_lib.push_back(head, 127)

        self.assertEqual(self.module_lib.front(head).value, 2003)
        self.assertEqual(self.module_lib.front(head).valid, 1)
        self.module_lib.pop_front(head)
        
        self.assertEqual(self.module_lib.front(head).value, 23)
        self.assertEqual(self.module_lib.front(head).valid, 1)
        self.module_lib.pop_front(head)

        self.assertEqual(self.module_lib.front(head).value, 3)
        self.assertEqual(self.module_lib.front(head).valid, 1)
        self.module_lib.pop_front(head)

        self.assertEqual(self.module_lib.front(head).value, 127)
        self.assertEqual(self.module_lib.front(head).valid, 1)
        self.module_lib.pop_front(head)

        self.assertEqual(self.module_lib.front(head).valid, 0)

    def test_back(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_back(head, 3)
        self.module_lib.push_front(head, 23)
        self.module_lib.push_front(head, 2003)
        self.module_lib.push_back(head, 127)

        self.assertEqual(self.module_lib.back(head).value, self.module_lib.value_at(head, 3).value)
        self.module_lib.pop_back(head)
        
        self.assertEqual(self.module_lib.back(head).value, self.module_lib.value_at(head, 2).value)
        self.module_lib.pop_back(head)

        self.assertEqual(self.module_lib.back(head).value, self.module_lib.value_at(head, 1).value)
        
        self.module_lib.pop_back(head)
        self.module_lib.pop_back(head)

        self.assertEqual(self.module_lib.back(head).valid, 0)

    def test_insert_invalid(self):
        head = self.module_lib.create_linkedlist()

        self.module_lib.insert(head, 1, 20) # do nothing
        self.assertEqual(self.module_lib.size(head), 0)
        self.assertTrue(self.module_lib.empty(head))
        
    def test_insert(self):
        head = self.module_lib.create_linkedlist()

        self.module_lib.insert(head, 0, 12321) # 12321
        self.module_lib.push_back(head, 69) # 12321 -> 69
        self.module_lib.push_front(head, 32); # 32 -> 12321 -> 69
        self.module_lib.insert(head, 2, 20) # 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 0, 12) # 12 -> 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 1, 2312) # 12 -> 2312 -> 32 -> 12321 -> 20 -> 69

        l = [12, 2312, 32, 12321, 20, 69]

        for i in range(6):
            self.assertEqual(self.module_lib.value_at(head, i).value, l[i])
    
    
    def test_erase(self):
        head = self.module_lib.create_linkedlist()

        self.module_lib.insert(head, 0, 12321) # 12321
        self.module_lib.push_back(head, 69) # 12321 -> 69
        self.module_lib.push_front(head, 32); # 32 -> 12321 -> 69
        self.module_lib.insert(head, 2, 20) # 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 0, 12) # 12 -> 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 1, 2312) # 12 -> 2312 -> 32 -> 12321 -> 20 -> 69

        l = [12, 2312, 32, 12321, 20, 69]

        self.module_lib.erase(head, 0) # head: 2312 -> 32 -> 12321 -> 20 -> 69 ->
        self.assertEqual(self.module_lib.size(head), 5)

        for _ in range(1, 6):
            self.assertEqual(self.module_lib.value_at(head, _ - 1).value, l[_])
        
        self.module_lib.erase(head, 5) # do nothing
        self.module_lib.erase(head, -1) # do nothing
        self.assertEqual(self.module_lib.size(head), 5)

        for _ in range(1, 6):
            self.assertEqual(self.module_lib.value_at(head, _ - 1).value, l[_])
        
        self.module_lib.erase(head, 4) # tail: 2312 -> 32 -> 12321 -> 20 ->
        self.assertEqual(self.module_lib.size(head), 4)

        for _ in range(1, 5):
            self.assertEqual(self.module_lib.value_at(head, _ - 1).value, l[_])
        
        self.module_lib.erase(head, 2) # middle: 2312 -> 32 -> 20 -> 
        self.assertEqual(self.module_lib.size(head), 3)

        l3 = [2312, 32, 20]
        for _ in range(0, 2):
            self.assertEqual(self.module_lib.value_at(head, _).value, l3[_])
        
        self.module_lib.erase(head, 1) # middle: 2312 -> 20 -> 
        self.assertEqual(self.module_lib.size(head), 2)

        l2 = [2312, 20]
        for _ in range(0, 1):
            self.assertEqual(self.module_lib.value_at(head, _).value, l2[_])
        
        self.module_lib.erase(head, 0)
        self.module_lib.erase(head, 0) # empty
        self.assertEqual(self.module_lib.value_at(head, 0).valid, 0)
        self.assertEqual(self.module_lib.size(head), 0)
        self.assertTrue(self.module_lib.empty(head))
    
    
    def test_value_n_from_end_invalid(self):
        head = self.module_lib.create_linkedlist()

        self.assertFalse(self.module_lib.value_n_from_end(head, 0).valid)
    
    def test_value_n_from_end(self):
        head = self.module_lib.create_linkedlist()

        for _ in range(0, 10):
            self.module_lib.insert(head, 0, _)
        
        for _ in range(0, 10):
            self.assertEqual(self.module_lib.value_n_from_end(head, _ + 1).value, _)
    
    def test_remove_value_empty(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.remove_value(head, 12) # do nothing
    
    def test_remove_value_first(self):
        head = self.module_lib.create_linkedlist()
        self.module_lib.push_front(head, 12)
        
        self.module_lib.remove_value(head, 13)
        self.module_lib.remove_value(head, 12)

        self.assertTrue(self.module_lib.empty(head))
    
    def test_remove_value(self):
        head = self.module_lib.create_linkedlist()

        self.module_lib.insert(head, 0, 12321) # 12321
        self.module_lib.push_back(head, 69) # 12321 -> 69
        self.module_lib.push_front(head, 32); # 32 -> 12321 -> 69
        self.module_lib.insert(head, 2, 20) # 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 0, 12) # 12 -> 32 -> 12321 -> 20 -> 69
        self.module_lib.insert(head, 1, 2312) # 12 -> 2312 -> 32 -> 12321 -> 20 -> 69

        self.assertEqual(self.module_lib.back(head).value, 69)
        self.module_lib.remove_value(head, 69) # tail
        self.assertEqual(self.module_lib.back(head).value, 20)
        self.assertEqual(self.module_lib.size(head), 5)

        self.assertEqual(self.module_lib.value_at(head, 2).value, 32)
        self.module_lib.remove_value(head, 32) # middle
        self.assertEqual(self.module_lib.value_at(head, 2).value, 12321)
        self.assertEqual(self.module_lib.size(head), 4)

        self.module_lib.remove_value(head, 32) # do nothing
        self.assertEqual(self.module_lib.value_at(head, 2).value, 12321)
        self.assertEqual(self.module_lib.size(head), 4)
    
    def test_reverse(self):
        head = self.module_lib.create_linkedlist()

        for _ in range(10):
            self.module_lib.push_back(head, _) # 0 1 2 3 4 5 ... 9
        
        self.module_lib.reverse(head) # 9 8 ... 1 0

        for _ in range(10):
            self.assertEqual(self.module_lib.value_at(head, _).value, 9 - _)



if __name__ == "__main__":
    try:
        unittest.main()
    except: 
        pass 
    
    

    






        