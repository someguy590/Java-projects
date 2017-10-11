package Main;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by someguy590 on 5/30/2016.
 */
public class HackerExperienceHelper {
    private HashSet<String> npcIps;
    private HashSet<String> vpcIps;
    private static final String NPC_IPS_FILE_NAME = "npc ips.dat";
    private static final String VPC_IPS_FILE_NAME = "vpc ips.dat";
    private static final String TODO_IPS_FILE_NAME = "todo ips.txt";
    private static final String OTHER_IPS_FILE_NAME = "other ips.txt";
    private static final String IP_REGEX = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";

    public HackerExperienceHelper() throws IOException, ClassNotFoundException {
        ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(NPC_IPS_FILE_NAME));
        Object o = inStream.readObject();
        if (o instanceof HashSet)
            npcIps = (HashSet<String>)o;

        inStream = new ObjectInputStream(new FileInputStream(VPC_IPS_FILE_NAME));
        o = inStream.readObject();
        if (o instanceof HashSet)
            vpcIps = (HashSet<String>)o;

        inStream.close();
    }

    public void getIps(File file) throws IOException {
        Scanner scanner = new Scanner(file);

        // Files to write ips to
        File todoIPFile = new File(TODO_IPS_FILE_NAME);
        File otherIPFile = new File(OTHER_IPS_FILE_NAME);

        BufferedWriter otherWriter = new BufferedWriter(new FileWriter(otherIPFile, true));

        Pattern p = Pattern.compile(IP_REGEX);

        // Get ips
        HashSet<String> todoIps = new HashSet<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher m = p.matcher(line);
            while (m.find()) {
                if (npcIps.contains(m.group()) || vpcIps.contains(m.group())) // Ip already known
                    continue;
                todoIps.add(m.group()); // Collect unique ips before writing
            }

            // Get bank account info, bitcoin info
            if (line.contains("#") || line.contains("key")) {
                otherWriter.write(line);
                otherWriter.newLine();
            }
        }
        otherWriter.close();

        // Write unique ips
        BufferedWriter todoWriter = new BufferedWriter(new FileWriter(todoIPFile, true));
        for (String ip : todoIps) {
            todoWriter.write(ip);
            todoWriter.newLine();
        }
        todoWriter.close();
    }

    public boolean addNpc(String ip) throws IOException {

        boolean result = npcIps.add(ip);
        writeNpc();
        return result;

    }

    public boolean addVpc(String ip) throws IOException {
        boolean result = vpcIps.add(ip);
        writeVpc();
        return result;
    }


    public boolean addAllNpc(Collection c) throws IOException {
        boolean result = npcIps.addAll(c);
        writeNpc();
        return result;
    }

    public boolean addAllVpc(Collection c) throws IOException {
        boolean result = vpcIps.addAll(c);
        writeVpc();
        return result;
    }

    private void writeNpc() throws IOException {
        // Write npc ips
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(NPC_IPS_FILE_NAME));
        outStream.writeObject(npcIps);
        outStream.close();
    }

    private void writeVpc() throws IOException {
        // Write vpc ips
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(VPC_IPS_FILE_NAME));
        outStream.writeObject(vpcIps);
        outStream.close();
    }

    public boolean isNpc(String ip) {
        return npcIps.contains(ip);
    }

    public boolean isVpc(String ip) {
        return vpcIps.contains(ip);
    }

    public boolean removeNpc(String ip) throws IOException {
        boolean result = npcIps.remove(ip);
        writeNpc();

        return result;
    }

    public boolean removeVpc(String ip) throws IOException {
        boolean result = vpcIps.remove(ip);
        writeVpc();

        return result;
    }

    public boolean containsIp(String string) {
        Pattern p = Pattern.compile(IP_REGEX);
        Matcher m = p.matcher(string);

        return m.find();
    }

    public boolean containsAccount(String string) {
        return string.contains("#");
    }

    public boolean containsKey(String string) {
        return string.contains("key");
    }



    private String extractIP(String string) throws IllegalStateException {
        Pattern p = Pattern.compile(IP_REGEX);
        Matcher m = p.matcher(string);

        return m.group();
    }




    public HashSet<String> getNpcIps() {
        return npcIps;
    }

    public HashSet<String> getVpcIps() {
        return vpcIps;
    }

}
