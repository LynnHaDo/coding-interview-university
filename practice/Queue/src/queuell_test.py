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

class QueueLLTest(unittest.TestCase):

    def setUp(self):
        self.module_lib = load("queuell")
    
    def test_create_queue(self):
        q = self.module_lib.create_queue()
        self.assertTrue(q)
        self.assertEqual(self.module_lib.empty(q), 1)
    
    def test_enqueue(self):
        q = self.module_lib.create_queue()
        self.module_lib.enqueue(q, 24)
        self.assertEqual(self.module_lib.empty(q), 0)
    
    def test_dequeue_empty(self):
        q = self.module_lib.create_queue()
        self.assertEqual(self.module_lib.dequeue(q).valid, 0)
        self.assertEqual(self.module_lib.dequeue(q).value, 0)
    
    def test_dequeue(self):
        q = self.module_lib.create_queue()
        self.module_lib.enqueue(q, 24)
        self.module_lib.enqueue(q, 23)
        self.module_lib.enqueue(q, 3)
        self.module_lib.enqueue(q, 2003)

        self.assertEqual(self.module_lib.empty(q), 0)

        self.assertEqual(self.module_lib.dequeue(q).value, 24)
        self.assertEqual(self.module_lib.dequeue(q).value, 23)
        self.assertEqual(self.module_lib.dequeue(q).value, 3)
        self.assertEqual(self.module_lib.dequeue(q).value, 2003)

if __name__ == "__main__":
    try:
        unittest.main()
    except: 
        pass 