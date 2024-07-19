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
        self.assertTrue(t)
        self.assertEqual(self.module_lib.size_tree(t), 0)
    
    def test_insert_one(self):
        t = self.module_lib.create_bst()
        self.module_lib.insert_tree(t, 10)
        self.assertEqual(self.module_lib.size_tree(t), 1)
        self.assertEqual(self.module_lib.get_root(t).value, 10) # check that it is the root
    
    def test_insert_multiple(self):
        t = self.module_lib.create_bst()

        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        self.assertEqual(self.module_lib.size_tree(t), len(items))
        self.assertEqual(self.module_lib.get_root(t).value, 10)
        self.assertEqual(self.module_lib.get_root(t).left.value, 4)
        self.assertEqual(self.module_lib.get_root(t).right.value, 12)
        self.assertEqual(self.module_lib.get_root(t).right.right.value, 100)
        self.assertEqual(self.module_lib.get_root(t).right.right.left.value, 13)
    
    def test_is_in_tree(self):
        t = self.module_lib.create_bst()

        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        for item in items:
            self.assertEqual(self.module_lib.is_in_tree(t, item), 1)
        
        self.assertEqual(self.module_lib.is_in_tree(t, 15), 0)
        self.assertEqual(self.module_lib.is_in_tree(t, 200), 0)
        self.assertEqual(self.module_lib.is_in_tree(t, 300), 0)
    
    def test_delete_tree(self):
        t = self.module_lib.create_bst()

        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        self.module_lib.delete_tree(t)
        self.assertEqual(self.module_lib.size_tree(t), 0)
        self.assertFalse(self.module_lib.get_root(t))
    
    def test_get_height_empty(self):
        t = self.module_lib.create_bst()

        self.assertEqual(self.module_lib.get_height(t), 1)
    
    def test_get_height(self):
        t = self.module_lib.create_bst()
        items = [10, 12, 4, 100, 13]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        self.assertEqual(self.module_lib.get_height(t), 3)

    def test_get_min_empty(self):
        t = self.module_lib.create_bst()

        self.assertEqual(self.module_lib.get_min(t).valid, 0)
    
    def test_get_min(self):
        t = self.module_lib.create_bst()
        items = [10, 12, 4, 100, 13, -2]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        self.assertEqual(self.module_lib.get_min(t).value, -2)
    
    def test_get_max_empty(self):
        t = self.module_lib.create_bst()

        self.assertEqual(self.module_lib.get_max(t).valid, 0)
    
    def test_get_max(self):
        t = self.module_lib.create_bst()
        items = [10, 1000, 12, 4, 100, 13, -2]

        for item in items:
            self.module_lib.insert_tree(t, item)
        
        self.assertEqual(self.module_lib.get_max(t).value, 1000)
    
    def test_validate_empty_binary_tree(self):
        t = self.module_lib.create_bst()

        self.assertEqual(self.module_lib.is_binary_search_tree(t), 1)
    
    def test_validate_valid_binary_tree(self):
        t = self.module_lib.create_bst()
        # root = [2,1,3]
        root = self.module_lib.get_new_node()
        root.value = 2
        root.left = self.module_lib.get_new_node()
        root.left.value = 1
        root.right = self.module_lib.get_new_node()
        root.right.value = 3
        t.root = root

        self.assertEqual(self.module_lib.is_binary_search_tree(t), 1) # true
    
    def test_validate_invalid_binary_tree(self):
        # root = [5,1,4,null,null,3,6]
        t = self.module_lib.create_bst()

        root = self.module_lib.get_new_node()
        root.value = 5
        root.left = self.module_lib.get_new_node()
        root.left.value = 1
        root.right = self.module_lib.get_new_node()
        root.right.value = 4 
        root.right.left = self.module_lib.get_new_node()
        root.right.left.value = 3
        root.right.right = self.module_lib.get_new_node()
        root.right.right.value = 6
        t.root = root

        self.assertEqual(self.module_lib.is_binary_search_tree(t), 0) # false
    
    def test_delete_value_two_children(self):
        # root = [5,3,6,2,4,null,7], key = 3
        # output = [5,4,6,2,null,null,7]
        t = self.module_lib.create_bst()

        root = self.module_lib.get_new_node()
        root.value = 5
        root.left = self.module_lib.get_new_node()
        root.left.value = 3
        root.right = self.module_lib.get_new_node()
        root.right.value = 6
        root.left.left = self.module_lib.get_new_node()
        root.left.left.value = 2
        root.left.right = self.module_lib.get_new_node()
        root.left.right.value = 4
        root.right.right = self.module_lib.get_new_node()
        root.right.right.value = 7
        t.root = root

        self.assertEqual(self.module_lib.is_in_tree(t, 3), 1)
        self.assertEqual(self.module_lib.delete_value(t, 3), 1) 
        self.assertEqual(self.module_lib.is_in_tree(t, 3), 0) # no longer in tree
    
    def test_delete_value_no_value(self):
        # root = [5,3,6,2,4,null,7], key = 0
        # output is the same

        t = self.module_lib.create_bst()

        root = self.module_lib.get_new_node()
        root.value = 5
        root.left = self.module_lib.get_new_node()
        root.left.value = 3
        root.right = self.module_lib.get_new_node()
        root.right.value = 6
        root.left.left = self.module_lib.get_new_node()
        root.left.left.value = 2
        root.left.right = self.module_lib.get_new_node()
        root.left.right.value = 4
        root.right.right = self.module_lib.get_new_node()
        root.right.right.value = 7
        t.root = root

        self.assertEqual(self.module_lib.delete_value(t, 0), 0) 
        self.assertEqual(self.module_lib.size_tree(t), 6)

    def test_delete_value_empty(self):
        # root = [], key = 0
        pass

    

        

if __name__ == '__main__':
    try:
        unittest.main()
    except:
        pass

