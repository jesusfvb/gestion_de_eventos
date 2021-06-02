package com.backend.backend.auxiliares.solicitudes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class ArchivoSoli implements MultipartFile {

    private Long size;

    private String originalFilename;

    private String name;

    private InputStream inputStream;

    private String contentType;

    private byte[] bytes;

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return bytes.length != 0;
    }

    @Override
    public void transferTo(File arg0) throws IOException, IllegalStateException {

    }

}
