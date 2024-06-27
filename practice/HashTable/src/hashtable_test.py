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

class HashTableTest(unittest.TestCase):

    def setUp(self):
        self.module_lib = load("hashtable")
    
    def test_add_one(self):
        h = self.module_lib.create_hashtable(5)

        self.module_lib.add(h, 23, 3)

        self.assertTrue(self.module_lib.exists(h, 23))
        self.assertEqual(self.module_lib.indexof(h, 23), 3)
        self.assertEqual(self.module_lib.get_key(h, 23).value, 3)
    
    def test_add_multiple(self):
        h = self.module_lib.create_hashtable(5)

        keys = [50, 70, 76, 85, 93]
        i = 0

        #self.assertFalse(self.module_lib.exists(h, 50))

        for key in keys:
            self.module_lib.add(h, key, i)
            i += 1

        for key in keys:
            self.assertTrue(self.module_lib.exists(h, key))
        
        i = 0

        for key in keys:
            self.assertEqual(self.module_lib.indexof(h, key), i)
            self.assertEqual(self.module_lib.get_key(h, key).value, i)
            i += 1
            

if __name__ == "__main__":
    try:
        unittest.main()
    except: 
        pass