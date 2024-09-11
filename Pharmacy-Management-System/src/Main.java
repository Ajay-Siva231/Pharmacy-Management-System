import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Map<String, Integer>> medicines = new HashMap<>();

    public static void initializeMedicines() {
        Map<String, Integer> ciploInfo = new HashMap<>();
        ciploInfo.put("price", 20);
        ciploInfo.put("stock", 50);

        Map<String, Integer> vicksInfo = new HashMap<>();
        vicksInfo.put("price", 15);
        vicksInfo.put("stock", 30);

        Map<String, Integer> aspirinInfo = new HashMap<>();
        aspirinInfo.put("price", 30);
        aspirinInfo.put("stock", 40);

        medicines.put("ciplo", ciploInfo);
        medicines.put("vicks", vicksInfo);
        medicines.put("aspirin", aspirinInfo);
    }

    public static void displayMedicines() {
        System.out.println("Medicines available:");
        int idx = 1;
        for (Map.Entry<String, Map<String, Integer>> entry : medicines.entrySet()) {
            String medicine = entry.getKey();
            Map<String, Integer> info = entry.getValue();
            int price = info.get("price");
            int stock = info.get("stock");
            System.out.printf("%d. %s: $%d | Stock: %d%n", idx++, medicine, price, stock);
        }
    }

    public static int userMenu() {
        System.out.println("\n User Menu");
        System.out.println("1. View medicines");
        System.out.println("2. Purchase medicines");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        return choice;
    }

    public static int adminMenu() {
        System.out.println("\n Admin Menu");
        System.out.println("1. View medicines");
        System.out.println("2. Add medicine");
        System.out.println("3. Purchase medicines");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        return choice;
    }

    public static void runUserInterface() {
        while (true) {
            int choice = userMenu();
            if (choice == 1) {
                displayMedicines();
            } else if (choice == 2) {
                medicinePurchase();
            } else if (choice == 3) {
                System.out.println("Exit user interface");
                String q = scanner.nextLine();
                role();
            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    public static void runadminInterface() {
        while (true) {
            int choice = adminMenu();
            if (choice == 1) {
                displayMedicines();
            } else if (choice == 2) {
                addMedicines();
            } else if (choice == 3) {
                medicinePurchase();
            }else if (choice == 4) {
                System.out.println("Exiting admin interface");
                String q = scanner.nextLine();
                role();
            } else {
                System.out.println("Invalid choice. Please try again");
            }
        }
    }

    public static void addMedicines() {
        System.out.print("Enter the name of the medicine: ");
        String q = scanner.nextLine();
        String medicineName = scanner.nextLine();

        System.out.print("Enter the price of the medicine: $");
        int medicinePrice = scanner.nextInt();

        System.out.print("Enter the initial stock quantity: ");
        int initialStock = scanner.nextInt();

        Map<String, Integer> medicineInfo = new HashMap<>();
        medicineInfo.put("price", medicinePrice);
        medicineInfo.put("stock", initialStock);

        medicines.put(medicineName, medicineInfo);
        System.out.println(medicineName + " added to the medicine list.");
    }

    public static void medicinePurchase() {
        displayMedicines();
        System.out.print("Enter the which medicine you want to purchase: ");
        int medicineName = scanner.nextInt();

        try {
            int medicineIdx = medicineName - 1;
            List<String> medicineKeys = new ArrayList<>(medicines.keySet());
            String selectedMedicine = medicineKeys.get(medicineIdx);

            System.out.print("Enter the quantity: ");
            String q = scanner.nextLine();
            int quantity = scanner.nextInt();

            if (quantity <= medicines.get(selectedMedicine).get("stock")) {
                int totalCost = medicines.get(selectedMedicine).get("price") * quantity;
                int updatedStock = medicines.get(selectedMedicine).get("stock") - quantity;
                medicines.get(selectedMedicine).put("stock", updatedStock);
                System.out.printf("Total cost for %d %s(s): $%d%n", quantity, selectedMedicine, totalCost);
                System.out.println("Remaining stock for " + selectedMedicine + ": " + medicines.get(selectedMedicine).put("stock", updatedStock));
            } else {
                System.out.println("Insufficient stock for " + selectedMedicine + ". Please choose a smaller quantity.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please enter a valid medicine number.");
        }
    }

    public static void role() {
        while (true) {
            System.out.println("Enter your role (user/admin)");
            String role = scanner.nextLine();

            if (role.equals("user")) {
                runUserInterface();
            } else if (role.equals("admin")) {
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();
                if (password.equals("Ajay")) {
                    runadminInterface();
                    break;
                } else {
                    System.out.println("Incorrect admin password. Access denied");
                }
            } else {
                System.out.println("Invalid role. Please enter 'user' or 'admin'");
            }
            scanner.close();
        }
    }
    public static void main(String[] args) {

        initializeMedicines();

        role();

    }
}