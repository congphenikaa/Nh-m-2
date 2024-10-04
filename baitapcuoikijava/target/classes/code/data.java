import java.io.File;

public class data {
    public static void main(String[] args) {
        // Đường dẫn đến thư mục Data
        String directoryPath = "Data";

        // Tạo đối tượng File
        File directory = new File(directoryPath);

        // Kiểm tra xem thư mục đã tồn tại chưa
        if (!directory.exists()) {
            // Tạo thư mục
            boolean created = directory.mkdir();
            if (created) {
                System.out.println("Thư mục 'Data' đã được tạo thành công.");
            } else {
                System.out.println("Không thể tạo thư mục 'Data'.");
            }
        } else {
            System.out.println("Thư mục 'Data' đã tồn tại.");
        }
    }
}
