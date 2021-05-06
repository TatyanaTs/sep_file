package sep_file;
import java.io.*;

class sep_file {
    public static void splitFile(File f, String fn) throws IOException {
        int partCounter = 1;

        int sizeOfFiles = 1024 * 1024 * 15;// 15MB
        byte[] buffer = new byte[sizeOfFiles];

        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fn, partCounter++);
                File newFile = new File(f.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File (args[0]);
        String fixed_name = args[1];
        splitFile(file, fixed_name);
    }
}
