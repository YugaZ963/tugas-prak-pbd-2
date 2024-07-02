// Mengimpor kelas dan paket yang diperlukan dari Java dan Swing untuk menjalankan aplikasi GUI dan manipulasi gambar.
import java.awt.BorderLayout; // Layout manager untuk mengatur komponen dalam lima area: North, South, East, West, dan Center.
import java.awt.Color; // Digunakan untuk menetapkan warna dalam aplikasi, seperti latar belakang, teks, atau border.
import java.awt.EventQueue; // Utilitas untuk menjadwalkan event GUI di Event Dispatch Thread (EDT).
import java.awt.Graphics2D; // Kelas untuk menggambar grafik 2D di objek seperti BufferedImage.
import java.awt.GridLayout; // Layout manager untuk mengatur komponen dalam bentuk grid (baris dan kolom).
import java.awt.Image; // Kelas dasar untuk semua representasi gambar dalam Java.
import java.awt.Point; // Kelas yang mewakili koordinat x dan y dalam grafik.
import java.awt.event.ActionEvent; // Digunakan untuk menggambarkan event yang terjadi dari tindakan pengguna, seperti klik tombol.
import java.awt.event.MouseAdapter; // Kelas adapter yang mempermudah implementasi event mouse (klik, gerakan, dsb.).
import java.awt.event.MouseEvent; // Kelas yang menggambarkan event mouse, seperti klik atau gerakan mouse.
import java.awt.image.BufferedImage; // Representasi gambar yang disimpan dalam memori.
import java.awt.image.CropImageFilter; // Filter gambar untuk memotong bagian tertentu dari gambar.
import java.awt.image.FilteredImageSource; // Sumber gambar yang menerapkan filter ke gambar.
import java.io.File; // Kelas yang mewakili file atau direktori di file system.
import java.io.IOException; // Exception yang ditangani ketika terjadi kesalahan input atau output.
import java.util.ArrayList; // Implementasi list yang dinamis untuk menyimpan elemen.
import java.util.Collections; // Kelas utilitas untuk operasi koleksi, seperti pengacakan atau pengurutan.
import java.util.List; // Antarmuka yang mewakili koleksi list dalam Java.
import java.util.logging.Level; // Menetapkan tingkat log yang digunakan oleh Logger.
import java.util.logging.Logger; // Kelas untuk mencatat informasi log di aplikasi.

import javax.imageio.ImageIO; // Kelas untuk membaca dan menulis gambar dalam berbagai format.
import javax.swing.AbstractAction; // Kelas abstrak yang menyediakan kerangka untuk menangani action event.
import javax.swing.BorderFactory; // Kelas utilitas untuk membuat objek border yang umum digunakan dalam Swing.
import javax.swing.ImageIcon; // Representasi gambar yang digunakan untuk ikon di komponen Swing, seperti tombol.
import javax.swing.JButton; // Komponen GUI yang merepresentasikan tombol.
import javax.swing.JComponent; // Kelas dasar untuk semua komponen Swing yang bisa dilihat.
import javax.swing.JFrame; // Komponen GUI yang merepresentasikan jendela utama.
import javax.swing.JLabel; // Komponen GUI untuk menampilkan teks atau gambar.
import javax.swing.JOptionPane; // Kelas untuk menampilkan kotak dialog yang menginformasikan atau meminta input dari pengguna.
import javax.swing.JPanel; // Komponen kontainer generik untuk menampung komponen GUI lain dengan layout yang ditentukan.


// Kelas MyButton yang merupakan turunan dari kelas JButton
class MyButton extends JButton {

    // Variabel instance untuk menandai apakah tombol ini adalah tombol terakhir
    private boolean isLastButton;

    // Konstruktor default tanpa parameter
    public MyButton() {
        super(); // Memanggil konstruktor JButton
        initUI(); // Memanggil metode initUI untuk menginisialisasi antarmuka pengguna
    }

    // Konstruktor dengan parameter gambar (Image)
    public MyButton(Image image) {
        super(new ImageIcon(image)); // Memanggil konstruktor JButton dengan ikon gambar
        initUI(); // Memanggil metode initUI untuk menginisialisasi antarmuka pengguna
    }

    // Metode untuk menginisialisasi antarmuka pengguna
    private void initUI() {
        isLastButton = false; // Mengatur bahwa tombol ini bukan tombol terakhir
        BorderFactory.createLineBorder(Color.gray); // Membuat border dengan warna abu-abu (gray)

        // Menambahkan mouse listener untuk menangani event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Mengubah border menjadi warna kuning saat mouse masuk ke area tombol
                setBorder(BorderFactory.createLineBorder(Color.yellow));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Mengembalikan border menjadi warna abu-abu saat mouse keluar dari area tombol
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }

    // Metode untuk mengatur tombol ini sebagai tombol terakhir
    public void setLastButton() {
        isLastButton = true; // Menandai bahwa tombol ini adalah tombol terakhir
    }

    // Metode untuk memeriksa apakah tombol ini adalah tombol terakhir
    public boolean isLastButton() {
        return isLastButton; // Mengembalikan nilai boolean apakah tombol ini adalah tombol terakhir
    }
}

/* Penjelasan Rinci
Kelas MyButton:

MyButton adalah kelas kustom yang mewarisi (extends) JButton. Kelas ini memungkinkan Anda untuk membuat tombol dengan fitur tambahan.

Variabel isLastButton:

Variabel ini digunakan untuk menandai apakah tombol ini adalah tombol terakhir dalam konteks tertentu (misalnya, dalam puzzle atau urutan tombol).
Konstruktor:

Konstruktor Default:

public MyButton(): Membuat instance JButton tanpa gambar dan memanggil metode initUI() untuk inisialisasi.
Konstruktor dengan Parameter Gambar:
public MyButton(Image image): Membuat instance JButton dengan ikon yang berasal dari gambar yang diberikan. Memanggil initUI() untuk inisialisasi.

Metode initUI:

Metode ini menginisialisasi antarmuka pengguna (user interface) untuk MyButton.
Mengatur isLastButton ke false, menandakan bahwa tombol ini bukan tombol terakhir.
Membuat border default dengan warna abu-abu.
Menambahkan MouseListener untuk mendeteksi ketika mouse memasuki atau meninggalkan area tombol.
mouseEntered: Mengubah border menjadi warna kuning saat mouse masuk ke area tombol.
mouseExited: Mengubah border kembali ke warna abu-abu saat mouse meninggalkan area tombol.

Metode setLastButton:

public void setLastButton(): Metode ini digunakan untuk menandai tombol ini sebagai tombol terakhir dengan mengubah nilai isLastButton menjadi true.

Metode isLastButton:

public boolean isLastButton(): Metode ini mengembalikan nilai isLastButton, yang digunakan untuk memeriksa apakah tombol ini adalah tombol terakhir atau tidak.
*/

// Kelas puzzle yang merupakan turunan dari JFrame
public class puzzle extends JFrame {

    // Deklarasi variabel instance
    private JPanel panel; // Panel untuk menampung tombol-tombol puzzle
    private BufferedImage source; // Gambar asli yang akan dipotong menjadi puzzle
    private BufferedImage resized; // Gambar yang telah diubah ukurannya
    private Image image; // Gambar yang akan digunakan untuk setiap potongan puzzle
    private MyButton lastButton; // Tombol untuk potongan terakhir puzzle
    private int width, height; // Lebar dan tinggi gambar
    
    // List untuk menyimpan tombol-tombol puzzle dan solusi yang benar
    private List<MyButton> buttons;
    private List<Point> solution;
    private JLabel solu; // Label untuk menampilkan informasi

    // Konstanta untuk jumlah tombol dan lebar yang diinginkan
    private final int NUMBER_OF_BUTTONS = 12;
    private final int DESIRED_WIDTH = 300;

    // Konstruktor kelas puzzle
    public puzzle() {
        initUI(); // Memanggil metode untuk inisialisasi antarmuka pengguna
    }

    // Metode untuk menginisialisasi antarmuka pengguna
    private void initUI() {
        // Menginisialisasi solusi puzzle dengan posisi yang benar
        solution = new ArrayList<>();
        solution.add(new Point(0, 0));
        solution.add(new Point(0, 1));
        solution.add(new Point(0, 2));
        solution.add(new Point(1, 0));
        solution.add(new Point(1, 1));
        solution.add(new Point(1, 2));
        solution.add(new Point(2, 0));
        solution.add(new Point(2, 1));
        solution.add(new Point(2, 2));
        solution.add(new Point(3, 0));
        solution.add(new Point(3, 1));
        solution.add(new Point(3, 2));
        
        // Membuat label untuk instruksi atau informasi tambahan
        solu = new JLabel("Tugas Puzzle Yuga");
        solu.setForeground(Color.black); // Mengatur warna teks menjadi hitam
        add(solu, BorderLayout.NORTH); // Menambahkan label ke bagian atas (North) dari frame

        // Inisialisasi list untuk tombol puzzle
        buttons = new ArrayList<>();

        // Membuat panel untuk menampung tombol-tombol puzzle
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray)); // Menambahkan border abu-abu
        panel.setLayout(new GridLayout(4, 3, 0, 0)); // Mengatur layout grid 4x3

        // Memuat dan mengubah ukuran gambar
        try {
            source = loadImage(); // Memuat gambar dari file
            int h = getNewHeight(source.getWidth(), source.getHeight()); // Menghitung tinggi baru berdasarkan lebar yang diinginkan
            resized = resizeImage(source, DESIRED_WIDTH, h, BufferedImage.TYPE_INT_ARGB); // Mengubah ukuran gambar

        } catch (IOException ex) {
            // Menangani kesalahan jika gambar gagal dimuat
            Logger.getLogger(puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Mendapatkan lebar dan tinggi gambar yang telah diubah ukurannya
        width = resized.getWidth(null);
        height = resized.getHeight(null);

        // Menambahkan panel ke bagian tengah (Center) dari frame
        add(panel, BorderLayout.CENTER);

        // Membuat tombol-tombol puzzle dari gambar yang telah dipotong
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // Memotong gambar menjadi potongan-potongan kecil
                image = createImage(new FilteredImageSource(resized.getSource(),
                        new CropImageFilter(j * width / 3, i * height / 4, (width / 3), height / 4)));
                
                MyButton button = new MyButton(image); // Membuat tombol puzzle dengan potongan gambar
                button.putClientProperty("position", new Point(i, j)); // Menyimpan posisi asli tombol

                if (i == 3 && j == 2) {
                    // Menandai tombol terakhir sebagai tombol kosong
                    lastButton = new MyButton();
                    lastButton.setBorderPainted(false); // Menghapus border
                    lastButton.setContentAreaFilled(false); // Menghapus area isi
                    lastButton.setLastButton(); // Menandai sebagai tombol terakhir
                    lastButton.putClientProperty("position", new Point(i, j));
                } else {
                    buttons.add(button); // Menambahkan tombol ke daftar tombol
                }
            }
        }

        // Mengacak urutan tombol-tombol puzzle
        Collections.shuffle(buttons);
        buttons.add(lastButton); // Menambahkan tombol terakhir ke daftar

        // Menambahkan tombol-tombol ke panel dan menambahkan aksi klik
        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            MyButton btn = buttons.get(i);
            panel.add(btn); // Menambahkan tombol ke panel
            btn.setBorder(BorderFactory.createLineBorder(Color.gray)); // Menambahkan border abu-abu
            btn.addActionListener(new ClickAction()); // Menambahkan aksi klik ke tombol
        }

        // Mengatur properti JFrame
        pack();
        setTitle("Puzzle");
        setResizable(false);
        setLocationRelativeTo(null); // Menempatkan frame di tengah layar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Mengatur aksi saat aplikasi ditutup
    }

    // Metode untuk menghitung tinggi baru gambar berdasarkan lebar yang diinginkan
    private int getNewHeight(int w, int h) {
        double ratio = DESIRED_WIDTH / (double) w; // Menghitung rasio lebar baru terhadap lebar asli
        int newHeight = (int) (h * ratio); // Menghitung tinggi baru berdasarkan rasio
        return newHeight;
    }

    // Metode untuk memuat gambar dari file
    private BufferedImage loadImage() throws IOException {
        BufferedImage bimg = ImageIO.read(new File("src/gambar/Library.jpeg")); // Memuat gambar dari direktori
        return bimg;
    }

    // Metode untuk mengubah ukuran gambar
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, type); // Membuat BufferedImage baru dengan ukuran yang diinginkan
        Graphics2D g = resizedImage.createGraphics(); // Mendapatkan objek Graphics2D
        g.drawImage(originalImage, 0, 0, width, height, null); // Menggambar gambar asli ke gambar baru dengan ukuran yang diubah
        g.dispose(); // Membebaskan sumber daya grafis
        return resizedImage;
    }

    // Kelas ClickAction untuk menangani aksi klik tombol puzzle
    private class ClickAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            checkButton(e); // Memeriksa tombol yang diklik
            checkSolution(); // Memeriksa apakah puzzle sudah terpecahkan
        }

        // Metode untuk memeriksa tombol yang diklik dan melakukan swap jika valid
        private void checkButton(ActionEvent e) {
            int lidx = 0; // Indeks tombol terakhir
            for (MyButton button : buttons) {
                if (button.isLastButton()) {
                    lidx = buttons.indexOf(button); // Menemukan indeks tombol terakhir
                }
            }

            JButton button = (JButton) e.getSource(); // Mendapatkan tombol yang diklik
            int bidx = buttons.indexOf(button); // Mendapatkan indeks tombol yang diklik

            // Memeriksa apakah tombol yang diklik bersebelahan dengan tombol terakhir
            if ((bidx - 1 == lidx) || (bidx + 1 == lidx) || (bidx - 3 == lidx) || (bidx + 3 == lidx)) {
                Collections.swap(buttons, bidx, lidx); // Menukar posisi tombol
                updateButtons(); // Memperbarui tampilan tombol
            }
        }

        // Metode untuk memperbarui tampilan tombol-tombol puzzle
        private void updateButtons() {
            panel.removeAll(); // Menghapus semua tombol dari panel
            for (JComponent btn : buttons) {
                panel.add(btn); // Menambahkan kembali tombol-tombol ke panel
            }
            panel.validate(); // Memvalidasi panel untuk memperbarui tampilannya
        }
    }

    // Metode untuk memeriksa apakah puzzle sudah terpecahkan
    private void checkSolution() {
        List<Point> current = new ArrayList<>();
        for (JComponent btn : buttons) {
            current.add((Point) btn.getClientProperty("position")); // Menyimpan posisi saat ini dari setiap tombol
        }

        // Memeriksa apakah daftar posisi saat ini sama dengan daftar solusi
        if (compareList(solution, current)) {
            JOptionPane.showMessageDialog(panel, "Finished", "SELAMAT >//<", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Metode untuk membandingkan dua list
    public static boolean compareList(List ls1, List ls2) {
        return ls1.toString().contentEquals(ls2.toString()); // Mengembalikan true jika kedua list sama
    }

   


    public static void main(String[] args) {
    // Memulai aplikasi di Event Dispatch Thread (EDT) menggunakan EventQueue.invokeLater.
    // EDT adalah thread khusus untuk menangani event GUI dan memastikan thread-safety.
    EventQueue.invokeLater(new Runnable() {

        // Metode run() akan dijalankan di dalam EDT.
        @Override
        public void run() {
            // Membuat instance baru dari kelas puzzle.
            puzzle puzzle = new puzzle();

            // Mengatur visibilitas frame puzzle agar terlihat di layar.
            puzzle.setVisible(true);
        }
    });
}
    
/*    Penjelasan Rinci
public static void main(String[] args):

Ini adalah metode main, titik masuk untuk aplikasi Java. Metode ini akan dipanggil oleh Java Virtual Machine (JVM) untuk memulai eksekusi program.
Metode ini public sehingga dapat diakses dari luar kelas.
Metode ini static sehingga dapat dipanggil tanpa membuat instance dari kelas tersebut.
String[] args adalah parameter yang dapat digunakan untuk menerima argumen command-line saat program dijalankan.
    
EventQueue.invokeLater(new Runnable() { ... }):

EventQueue.invokeLater adalah metode yang digunakan untuk menempatkan tugas di antrian event Swing dan menjalankannya di Event Dispatch Thread (EDT).
EDT adalah thread khusus yang digunakan untuk menangani event-event GUI di aplikasi Swing, seperti klik tombol atau perubahan layout, untuk memastikan bahwa perubahan GUI aman dari masalah thread (thread-safe).
Runnable adalah antarmuka yang diimplementasikan oleh kelas yang ingin menjalankan kode di dalam metode run.
    
new Runnable() { ... }:

Membuat instance baru dari kelas Runnable dengan menyediakan implementasi dari metode run.
Runnable adalah antarmuka fungsional yang hanya memiliki satu metode, run, yang perlu diimplementasikan.
    
@Override:

Anotasi @Override menunjukkan bahwa metode run ini menggantikan (override) metode dari antarmuka Runnable.
    
public void run():

Metode run adalah tempat di mana kode yang ingin dijalankan di EDT diletakkan.
Di dalam metode ini, kita menempatkan kode yang menginisialisasi GUI aplikasi.
    
puzzle puzzle = new puzzle();:

Membuat instance baru dari kelas puzzle.
puzzle adalah kelas yang mewarisi JFrame dan mengatur komponen-komponen puzzle serta logika permainan.
    
puzzle.setVisible(true);:

Mengatur visibilitas dari frame puzzle agar terlihat di layar.
Jika tidak dipanggil, frame akan tetap ada dalam memori tetapi tidak akan ditampilkan.
        
*/        
    
}


