package classfile;

import java.io.IOException;

public class SourceCodeBuilder implements Appendable, CharSequence {

    StringBuilder buf;

    public SourceCodeBuilder() {
        buf = new StringBuilder();
    }

    public SourceCodeBuilder(int capacity) {
        buf = new StringBuilder(capacity);
    }

    public SourceCodeBuilder append(CharSequence csq) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SourceCodeBuilder append(CharSequence csq, int start, int end) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SourceCodeBuilder append(char c) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int length() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public char charAt(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CharSequence subSequence(int start, int end) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
