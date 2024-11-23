package autofix.core.file;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutputStream extends ObjectOutputStream {
	public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Do not write a header, Serialization error fix
        reset();
    }
}
