package classfile.constant;

import classfile.CpInfo;

/**
4.5.5 The CONSTANT_Long_info and CONSTANT_Double_info Structures
The CONSTANT_Long_info and CONSTANT_Double_info represent 8-byte
numeric (long and double) constants:
CONSTANT_Long_info {
u1 tag;
u4 high_bytes;
u4 low_bytes;
}
CONSTANT_Double_info {
u1 tag;
u4 high_bytes;
u4 low_bytes;
}
All 8-byte constants take up two entries in the constant_pool table of the class
file. If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item
in the constant_pool table at index n, then the next usable item in the pool is
located at index n +2. The constant_pool index n +1 must be valid but is
considered unusable.2
2 In retrospect, making 8-byte constants take two constant pool entries was a
poor choice.

The items of these structures are as follows:
tag
The tag item of the CONSTANT_Long_info structure has the
value CONSTANT_Long (5).
The tag item of the CONSTANT_Double_info structure has
the value CONSTANT_Double (6).

high_bytes, low_bytes
The unsigned high_bytes and low_bytes items of the
CONSTANT_Long_info structure together represent the value of
the long constant ((long) high_bytes &lt;&lt; 32) + low_bytes,
where the bytes of each of high_bytes and low_bytes are
stored in big-endian (high byte first) order.
The high_bytes and low_bytes items of the
CONSTANT_Double_info structure together represent the double
value in IEEE 754 floating-point double format (§3.3.2)(§3.3.2).
The bytes of each item are stored in big-endian (high byte first)
order.
The value represented by the CONSTANT_Double_info
structure is determined as follows. The high_bytes and
low_bytes items are first converted into the long constant bits,
which is equal to ((long) high_bytes &lt;&lt; 32) + low_bytes.
Then:
• If bits is 0x7ff0000000000000L, the double value will be
positive infinity.
• If bits is 0xfff0000000000000L, the double value will be
negative infinity.
• If bits is in the range 0x7ff0000000000001L through
0x7fffffffffffffffL or in the range 0xfff0000000000001L
through 0xffffffffffffffffL, the double value will be NaN.
• In all other cases, let s, e, and m be three values that might be
computed from bits:
int s = ((bits >> 63) == 0) ? 1 : -1;
int e = (int)((bits >> 52) & 0x7ffL);
long m = (e == 0) ?
(bits & 0xfffffffffffffL) &lt;&lt; 1 :
(bits & 0xfffffffffffffL) | 0x10000000000000L;
 */
public class Numeric8Info extends CpInfo {

    public Numeric8Info() {
    }
    public int highBytes;
    public int lowBytes;

}
