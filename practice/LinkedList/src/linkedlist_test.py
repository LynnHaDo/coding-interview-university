# Set up borrowed from https://github.com/branbick/unit-test-c-code-via-python/blob/main/examples/2_multiple_tests/sum_ints_test.py
from cffi import FFI  # type: ignore # load
from subprocess import run, PIPE
from importlib import import_module  # load
from unittest import TestCase  # AddIntsTest

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


class LinkedListTest(TestCase):

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
    
    def test_push_front_loop(self):
        head = self.module_lib.create_linkedlist()
        for i in range(0, 9):
            self.module_lib.push_front(head, i)
        self.assertEqual(self.module_lib.size(head), 9)
        self.assertEqual(self.module_lib.value_at(head, 4), 4)

        