package classfile.constant;

import classfile.CpInfo;

/**
4.5.4 The CONSTANT_Integer_info and CONSTANT_Float_info Structures
The CONSTANT_Integer_info and CONSTANT_Float_info structures represent
4-byte numeric (int and float) constants:
CONSTANT_Integer_info {
u1 tag;
u4 bytes;
}
CONSTANT_Float_info {
u1 tag;
u4 bytes;
}
The items of these structures are as follows:
tag
The tag item of the CONSTANT_Integer_info structure has the
value CONSTANT_Integer (3).
The tag item of the CONSTANT_Float_info structure has the
value CONSTANT_Float (4).
 */
public class Numeric4Info extends CpInfo {

    public Numeric4Info() {
    }
    /**
    The bytes item of the CONSTANT_Integer_info structure
    represents the value of the int constant. The bytes of the value
    are stored in big-endian (high byte first) order.
    The bytes item of the CONSTANT_Float_info structure
    represents the value of the float constant in IEEE 754 floatingpoint
    single format (§3.3.2)(§3.3.2). The bytes of the single
    format representation are stored in big-endian (high byte first)
    order.
    The value represented by the CONSTANT_Float_info
    structure is determined as follows. The bytes of the value are first
    converted into an int constant bits. Then:
    • If bits is 0x7f800000, the float value will be positive infinity.
    • If bits is 0xff800000, the float value will be negative infinity.
    • Ifbits is in the range 0x7f800001 through 0x7fffffff or in the
    range 0xff800001 through 0xffffffff, the float value will
    be NaN.
    • In all other cases, let s, e, and m be three values that might be
    computed from bits:
    int s = ((bits >> 31) == 0) ? 1 : -1;
    int e = ((bits >> 23) & 0xff);
    int m = (e == 0) ?
    (bits & 0x7fffff) &lt;&lt; 1 :
    (bits & 0x7fffff) | 0x800000;
    Then the float value equals the result of the mathematical
    expression s ⋅ m ⋅ 2^(e – 150).
     */
    public int bytes;

}
