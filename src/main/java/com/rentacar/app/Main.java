/* package com.rentacar.app;

import com.rentacar.util.PasswordUtil;

public class Main {
    public static void main(String[] args) {
        String plainPassword = "123456";
        String hashed = PasswordUtil.hashPassword(plainPassword);
        System.out.println("Hashlenmiş şifre: " + hashed);
    }
}

 */

package com.rentacar.app;

import com.rentacar.model.User;
import com.rentacar.service.RentalService;
import com.rentacar.service.UserService;
import com.rentacar.service.VehicleService;
import com.rentacar.model.Vehicle;


import java.util.Scanner;


public class Main {
    private static final RentalService rentalService = new RentalService();
    private static final UserService userService = new UserService();
    private static final VehicleService vehicleService = new VehicleService();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Araç Kiralama Uygulaması ===");

        // Giriş
        while (currentUser == null) {
            System.out.print("E-posta: ");
            String email = scanner.nextLine();

            System.out.print("Şifre: ");
            String password = scanner.nextLine();

            currentUser = userService.login(email, password);

            if (currentUser == null) {
                System.out.println("❌ Giriş başarısız. Lütfen tekrar deneyin.\n");
            }
        }

        System.out.println("✅ Hoş geldiniz, " + currentUser.getName() + "!");

        if (currentUser.getType().equalsIgnoreCase("ADMIN")) {
            adminMenusu(scanner);
        } else {
            kullaniciMenusu(scanner);
        }

        scanner.close();
    }

    // Admin için araç ekleme menüsü
    public static void adminMenusu(Scanner scanner) {
        System.out.println("🔧 Admin Paneli – Araç Ekle");

        while (true) {
            System.out.print("Araç türü (CAR / MOTORCYCLE / HELICOPTER): ");
            String type = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.print("Fiyat (TL): ");
            double price = Double.parseDouble(scanner.nextLine());

            vehicleService.addVehicle(currentUser, type, model, price);

            System.out.print("Yeni araç eklemek ister misiniz? (evet/hayır): ");
            String devam = scanner.nextLine();
            if (!devam.equalsIgnoreCase("evet")) {
                break;
            }
        }

        System.out.println("🔙 Ana menüye dönülüyor...");
    }

    // Müşteri menüsü: listeleme ve filtreleme işlemlerini yönetir
    public static void kullaniciMenusu(Scanner scanner) {
        while (true) {
            // Menü başlığı
            System.out.println("\n🚗 Müşteri Menüsü");
            System.out.println("1 - Tüm araçları listele");
            System.out.println("2 - Araçları kategoriye göre filtrele");
            System.out.println("3 - Araç kirala");
            System.out.println("4 - Kiralama geçmişini görüntüle");
            System.out.println("0 - Çıkış");

            // Kullanıcıdan seçim al
            System.out.print("Seçiminiz: ");
            String secim = scanner.nextLine();

            // Seçime göre işlem yap
            switch (secim) {
                case "1":
                    vehicleService.listAllVehicles();
                    break;
                case "2":
                    System.out.print("Kategori girin (CAR / MOTORCYCLE / HELICOPTER): ");
                    String kategori = scanner.nextLine();
                    vehicleService.listVehiclesByType(kategori);
                    break;
                case "3":
                    System.out.print("Araç ID'si girin: ");
                    int vehicleId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Süre tipi (HOUR / DAY / WEEK / MONTH): ");
                    String type = scanner.nextLine();

                    System.out.print("Süre (kaç " + type.toLowerCase() + "): ");
                    int value = Integer.parseInt(scanner.nextLine());

                    Vehicle v = vehicleService.findById(vehicleId);
                    if (v == null) {
                        System.out.println("❌ Araç bulunamadı.");
                    } else {
                        rentalService.rentVehicle(currentUser, v, type, value);
                    }
                    break;
                case "4":
                    rentalService.listUserRentals(currentUser);
                    break;
                case "0":
                    System.out.println("👋 Görüşmek üzere!");
                    return;
                default:
                    System.out.println("❌ Geçersiz seçim.");
            }

        }
    }

}


