package util;

import classfile.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugUtil {

    public static final String getObjectText(Object obj, int ident) {
        try {
            StringBuilder buf = new StringBuilder();
            appendObjectText(buf, obj, ident);
            return buf.toString();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static final void appendObjectText(StringBuilder buf, Object obj, int ident) throws IllegalAccessException {
        if (obj == null) {
            buf.append("null\r\n");
            return;
        }

        Class cls = obj.getClass();
        if (cls.isArray()) {
            buf.append(" ( ").append(cls.getName()).append(" )\r\n");
            if (cls == byte[].class) {
                appendHexText(buf, (byte[]) obj, ident);
            } else {
                for (int i = 0, len = Array.getLength(obj); i < len; ++i) {
                    for (int j = 0; j < ident; ++j) {
                        buf.append(' ');
                    }
                    Object value = Array.get(obj, i);
                    buf.append(i).append(" ( ").append(value.getClass().getName()).append(" )>\r\n");
                    appendObjectText(buf, value, ident + 1);
                }
            }
        } else {
            Field[] declaredFields = cls.getFields();
            for (Field f : declaredFields) {
                if ((f.getModifiers() & Modifier.STATIC) != 0) {
                    continue;
                }

                for (int i = 0; i < ident; ++i) {
                    buf.append(' ');
                }
                Object value = f.get(obj);
                buf.append(f.getName());

                if (value != null) {
                    Class vcls = value.getClass();
                    buf.append(" ( ").append(vcls.getName()).append(" ): ");
                    if (vcls.isArray()) {
                        buf.append("\r\n");
                        if (vcls == byte[].class) {
                            if (f.getName().equals("code")) {
                                appendDisassembledText(buf, (byte[]) value, ident);
                            } else {
                                appendHexText(buf, (byte[]) value, ident);
                            }
                        } else {
                            for (int i = 0, len = Array.getLength(value); i < len; ++i) {
                                for (int j = 0; j < ident; ++j) {
                                    buf.append(' ');
                                }
                                buf.append(i).append(">\r\n");
                                appendObjectText(buf, Array.get(value, i), ident + 1);
                            }
                        }
                    } else {
                        if (value instanceof String) {
                            buf.append(value);
                        } else if (value instanceof Byte) {
                            buf.append("0x").append(Integer.toHexString(0xff & (Byte) value));
                        } else if (value instanceof Short) {
                            buf.append("0x").append(Integer.toHexString(0xffff & (Short) value));
                        } else if (value instanceof Integer) {
                            buf.append("0x").append(Long.toHexString(0xffffffff & (Integer) value));
                        } else if (value instanceof Long) {
                            buf.append(Long.toString((Long) value));
                        } else if (cls.getPackage() == ClassFile.class.getPackage()) {
                            appendObjectText(buf, value, ident + 1);
                        } else {
                            buf.append(value);
                        }
                    }
                } else {
                    buf.append(" ( ").append(f.getType().getName()).append(" ): null");
                }
                buf.append("\r\n");
            }

        }
    }

    public static final void appendDisassembledText(StringBuilder buf, byte[] code, int ident) {
        if (code != null) {
            appendDisassembledText(buf, code, ident, 0, code.length);
        }
    }

    public static final void appendDisassembledText(StringBuilder buf, byte[] code, int ident, int start, int end) {

        if (code != null) {
            String asmText = Opcode.disassemble(code, start, end);
            BufferedReader br = new BufferedReader(new StringReader(asmText));

                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        for (int i = 0; i < ident; ++i) {
                            buf.append(' ');
                        }
                        buf.append(line).append("\r\n");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DebugUtil.class.getName()).log(Level.SEVERE, null, ex);
                }


        }
    }

    public static final void appendHexText(StringBuilder buf, byte[] info, int ident) {
        if (info != null) {
            appendHexText(buf, info, ident, 0, info.length);
        }
    }

    public static final void appendHexText(StringBuilder buf, byte[] info, int ident, int start, int end) {
        if (info != null) {
            for (; start < end;) {
                for (int i = 0; i < ident; ++i) {
                    buf.append(' ');
                }
                for (int j = 0; start < end && j < 16; ++j) {
                    buf.append(hex[0xff & info[start++]]).append(' ');
                }
                buf.append("\r\n");
            }
        }
    }
    private static final String[] hex;

    static {
        hex = new String[256];
        int i = 0;
        for (; i < 0x10; ++i) {
            hex[i] = "0" + Integer.toHexString(i);
        }
        for (; i < 0x100; ++i) {
            hex[i] = Integer.toHexString(i);
        }
    }
}
