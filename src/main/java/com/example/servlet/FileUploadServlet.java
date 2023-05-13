package com.example.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", value = "/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "/uploads"; // 指定文件上传目录的相对路径

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/")) {
            Part filePart = request.getPart("file");
            String fileName = getSubmittedFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            String fileUrl = saveFile(fileName, fileContent, request);
            response.getWriter().write(fileUrl);
        } else {
            response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }

    private String getSubmittedFileName(Part filePart) {
        String contentDisp = filePart.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    private String saveFile(String fileName, InputStream fileContent, HttpServletRequest request) throws IOException {
        String newFileName = generateUniqueFileName(fileName); // 生成较短的文件名
        String fullPath = request.getServletContext().getRealPath(UPLOAD_DIRECTORY);
        File fileDirectory = new File(fullPath);
        if(!fileDirectory.exists()){
            fileDirectory.mkdir();
        }
        File file = new File(fileDirectory, newFileName);
        try (OutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        String fileUrl = request.getContextPath() + UPLOAD_DIRECTORY + "/" + newFileName; // 相对路径
        return fileUrl;
    }

    private String generateUniqueFileName(String fileExtension) {
        long timestamp = System.currentTimeMillis();
        String uniqueFileName = Long.toString(timestamp);
        if (!fileExtension.isEmpty()) {
            uniqueFileName += "_" + fileExtension;
        }
        return uniqueFileName;
    }
}