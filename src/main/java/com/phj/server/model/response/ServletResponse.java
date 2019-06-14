package com.phj.server.model.response;

import com.phj.server.util.StreamUtil;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServletResponse implements Closeable {

    private OutputStream outputStream;
    private InputStream inputStream;

    public ServletResponse(InputStream inputStream,OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null ) {
            StreamUtil.close(inputStream);
        }

        if (outputStream != null ) {
            StreamUtil.close(outputStream);
        }
    }
}
