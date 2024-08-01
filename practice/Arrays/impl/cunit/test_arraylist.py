from ctypes import CDLL

import pytest # type: ignore

from tests.cunit import SRC, compile 


@pytest.fixture 
def arraylist():
    source = SRC / "arraylist.c"
    compile(source, cflags=["-g"])
    yield CDLL(str(source.with_suffix(".so")))

NULL = 0

def test_create_arraylist(arraylist):
    arr = arraylist.create_arraylist()
    assert arr
