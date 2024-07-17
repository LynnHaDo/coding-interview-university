# Set up borrowed from https://github.com/branbick/unit-test-c-code-via-python/blob/main/examples/2_multiple_tests/sum_ints_test.py
from cffi import FFI  # type: ignore # load
from subprocess import run, PIPE
from importlib import import_module  # load
import unittest  # AddIntsTest

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

class BSTTest(unittest.TestCase):
    def setUp(self):
        self.module_lib = load("bst")
    
    def test_create_bst(self):
        t = self.module_lib.create_bst()
        root = self.module_lib.get_root(t)
        self.assertTrue(t)
        self.assertEqual(self.module_lib.size(root), 0)
    
    def test_insert_one(self):
        t = self.module_lib.create_bst()
        self.module_lib.insert(t, 10)
        self.assertEqual(self.module_lib.size(self.module_lib.get_root(t)), 1)
        self.assertEqual(self.module_lib.get_root(t).value, 10) # check that it is the root
    
    def test_insert_multiple(self):
        t = self.module_lib.create_bst()

        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert(t, item)
        
        self.assertEqual(self.module_lib.size(self.module_lib.get_root(t)), len(items))
        self.assertEqual(self.module_lib.get_root(t).value, 10)
        self.assertEqual(self.module_lib.get_root(t).left.value, 4)
        self.assertEqual(self.module_lib.get_root(t).right.value, 12)
        self.assertEqual(self.module_lib.get_root(t).right.right.value, 100)
        self.assertEqual(self.module_lib.get_root(t).right.right.left.value, 13)
    
    def test_is_in_tree(self):
        t = self.module_lib.create_bst()

        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert(t, item)
        
        for item in items:
            self.assertEqual(self.module_lib.is_in_tree(t, item), 1)
        
        self.assertEqual(self.module_lib.is_in_tree(t, 15), 0)
        self.assertEqual(self.module_lib.is_in_tree(t, 200), 0)
        self.assertEqual(self.module_lib.is_in_tree(t, 300), 0)
    
    def test_delete_tree(self):
        
        
        

if __name__ == '__main__':
    try:
        unittest.main()
    except:
        pass

