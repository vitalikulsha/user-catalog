package util.validator;

import domain.Role;
import util.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RoleValidator implements Validator<Role> {

    //    private final static int MAX_AMOUNT = 2;
    private final static String MAX_AMOUNT = "roleMaxAmount";

    @Override
    public Role getValue(BufferedReader reader, Integer index) throws IOException {
        while (true) {
            System.out.print("The user can have from 1 to 2 roles. Enter #" + index + " user role: ");
            String role = reader.readLine().toUpperCase();
            if (!isRole(role)) {
                System.out.println("Incorrect role: " + role);
            } else {
                return Role.valueOf(role);
            }
        }
    }

    @Override
    public String getFlag(BufferedReader reader, List<Role> roles) throws IOException {
        int maxAmount = Integer.parseInt(Property.getValue(MAX_AMOUNT));
        if (roles.size() != maxAmount) {
            System.out.print("Add another role?(Y/N): ");
            return reader.readLine();
        } else {
            System.out.println("You have entered the maximum number of roles allowed");
            return "N";
        }
    }

    @Override
    public boolean verifyAndBreak(List<Role> roles, Role role) {
        if (roles.isEmpty() && role.equals(Role.SUPER_ADMIN)) {
            System.out.println("You have selected the role of SUPER_ADMIN, another role cannot be selected");
            return false;
        } else if (roles.contains(Role.SUPER_ADMIN)) {
            System.out.println("The user has the SUPER_ADMIN role, other roles cannot be selected.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean verifyAndContinue(List<Role> roles, Role role) {
        if (roles.contains(role)) {
            System.out.println("The user already has the " + role.name() + " role selected.");
            return false;
        } else if (roles.stream().anyMatch(r -> r.getLevel() == role.getLevel())) {
            System.out.println("The user already has a level " + role.getLevel() + " role selected.");
            return false;
        } else if (!roles.isEmpty() && role.equals(Role.SUPER_ADMIN)) {
            System.out.println("You cannot select the SUPER_ADMIN role because the user already has the " +
                    roles.get(0) + " role selected.");
            return false;
        } else {
            return true;
        }
    }

    private boolean isRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }
}
