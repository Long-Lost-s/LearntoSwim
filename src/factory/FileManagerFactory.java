package factory;

import file.BinaryFileManager;
import file.CSVFileManager;
import file.FileOperations;

public class FileManagerFactory {
    public static FileOperations getFileManager(String fileType) {
        if (fileType.equalsIgnoreCase("binary")) {
            return new BinaryFileManager();
        } else if (fileType.equalsIgnoreCase("csv")) {
            return new CSVFileManager();
        }
        throw new IllegalArgumentException("Invalid file type");
    }
}
