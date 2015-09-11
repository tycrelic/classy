package classfile.constant;

import classfile.CpInfo;

/**
4.5.7 The CONSTANT_Utf8_info Structure
The CONSTANT_Utf8_info structure is used to represent constant string values.
String content is encoded in modified UTF-8.
Modified UTF-8 strings are encoded so that character sequences that
contain only non-null ASCII characters can be represented using only 1
byte per character, but all Unicode characters can be represented. All
characters in the range '\u0001' to '\u007F' are represented by a single
byte:
[0|bits 6-0]
The 7 bits of data in the byte give the value of the character represented. The null
character ('\u0000') and characters in the range '\u0080' to '\u07FF' are represented
by a pair of bytes x and y:
x: [1 |1 |0 |bits 10-6] y: [1 |0 |bits 5-0]
The bytes represent the character with the value ((x & 0x1f) &lt; 6) + (y & 0x3f).
Characters in the range '\u0800' to '\uFFFF' are represented by 3 bytes x, y,
and z:
x: [1 |1 |1 |0 |bits 15-12] y: [1 |0 |bits 11-6] z: [1 |0 |bits 5-0]
The character with the value ((x & 0xf) &lt;&lt; 12) + ((y & 0x3f) &lt;&lt; 6) + (z & 0x3f) is
represented by the bytes.
Characters with code points above U+FFFF (so-called supplementary characters)
are represented by separately encoding the two surrogate code units of their UTF-
16 representation. Each of the surrogate code units is represented by three bytes.
This means, supplementary characters are represented by six bytes, u, v, w, x, y,
and z:
u: [1 1 1 0 1 1 0 1] v: [1 0 1 0 |(bits 20-16)-1] w: [1 0 |bits 15-10]
x: [1 1 1 0 1 1 0 1] y: [1 0 1 1 |bits 9-6] z: [1 0 |bits 5-0]
The character with the value
0x10000+((v&0x0f)&lt;16)+((w&0x3f)&lt;10)+(y&0x0f)&lt;&lt;6)+(z&0x3f) is
represented by the six bytes.
The bytes of multibyte characters are stored in the class file in big-endian (high
byte first) order.
There are two differences between this format and the “standard” UTF-8 format.
First, the null character (char)0 is encoded using the 2-byte format rather than
the 1-byte format, so that modified UTF-8 strings never have embedded nulls.
Second, only the 1-byte, 2-byte, and 3-byte formats of standard UTF-8 are used.
The Java VM does not recognize the four-byte format of standard UTF-8; it uses
its own two-times-three-byte format instead.
For more information regarding the standard UTF-8 format, see section 3.9
Unicode Encoding Forms of The Unicode Standard, Version 4.0.
The CONSTANT_Utf8_info structure is
CONSTANT_Utf8_info {
u1 tag;
u2 length;
u1 bytes[length];
}
The items of the CONSTANT_Utf8_info structure are the following:
tag
The tag item of the CONSTANT_Utf8_info structure has the
value CONSTANT_Utf8 (1).
 */
public class Utf8Info extends CpInfo {

    {
        tag = UTF8;
    }
    /**
    The value of the length item gives the number of bytes in the
    bytes array (not the length of the resulting string). The strings in
    the CONSTANT_Utf8_info structure are not null-terminated.
     */
    public short length;
    /**
    The bytes array contains the bytes of the string. No byte may
    have the value (byte)0 or lie in the range (byte)0xf0-
    (byte)0xff.
     */
    public byte[] bytes;//[length];
}
