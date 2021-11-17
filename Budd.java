package it1;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class Budd {
    public static void main(String[] args) throws IOException {

        //filepath和filename名字不一致等会看看
        String filepath = "D:\\mybyte\\fos.txt";
        String name = null;//数据没用到，显示数据有这些属性
        int age;
        int grade;
        String major;
        Budd b = new Budd();//构造无参的构造方法
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("哈尔滨理工大学软件与微电子学院学生通讯录");
            System.out.println("    1.增加信息");
            System.out.println("    2.删除信息");
            System.out.println("    3.修改信息");
            System.out.println("    4.查找信息");

            System.out.println("请输入您要进行的操作");
            int index = sc.nextInt();

            switch (index) {
                case 1:
                    System.out.println("1.增加信息");
                    b.add();
                    break;
                case 2:
                    System.out.println("2.删除信息");
                    b.delete();
                    break;
                case 3:
                    System.out.println("3.修改信息");
                    b.change();
                    break;
                case 4:
                    System.out.println("4.查找信息");//查找功能除了可以查找之外，还可以显示所有学生信息的功能match
                    b.sad();
                    break;
                default:
                    System.out.println("这是不正确的输入，请重新输入");
                    break;
            }
        }
    }

    void add() throws IOException {
        //true实现了数据写入文件的不可擦除性
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\mybyte\\fos.txt", true));
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入添加信息的条数");
        int dex = sc.nextInt();//设置增加的次数
        System.out.println("姓名     年龄     年级     专业");
        for (int i = 0; i <= dex; i++) {
            String s = sc.nextLine();
            bw.write(s);
            bw.newLine();
            //
            // bw.flush();
            if (i == dex) {
                System.out.println("添加成功");
            }
        }
        bw.close();
    }

    void delete() throws IOException {
        //删除的基本思想是:
        // 第一种是在文件中读出不符合删除条件的数据放入list集合中，然后再把list集合中的数据重新写入文件，创造了一个文件
        //第二种是创造了两个文件，将不符合删除条件的数据放入第二个文件中，然后重命名第二个文件。

        String filename = "D:\\mybyte\\fos.txt";
        List<String> line = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        Iterator<String> iterator = line.iterator();
        System.out.println("请输入你要删除的信息");
        Scanner sc = new Scanner(System.in);
        String wrong = sc.nextLine();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals(wrong)) {
                iterator.remove();
            }
        }
        System.out.println("剩余的信息为");
        System.out.println("姓名     年龄     年级     专业");
        //在控制台上输出更改后的文件内容
        line.forEach(System.out::println);

        //向文件中写入” “，相当于清空文件
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(" ");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //最后一个任务就是把更改后的list集合写入文件
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (String s : line) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }


    void change() throws IOException {
        //目前管理系统1.0版本只支持修改一行数据，哈哈哈哈
        String filename = "D:\\mybyte\\fos.txt";
        ArrayList<String> line = new ArrayList<>(Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8));
        Iterator<String> iterator = line.iterator();
        System.out.println("请输入你要更改的信息");
        Scanner sc = new Scanner(System.in);
        String wrongb = sc.nextLine();

        System.out.println("请输入更改的数据");
        String wronga = sc.nextLine();


        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals(wrongb)) {
                iterator.remove();
            }
        }
        line.add(wronga);
        System.out.println("更改后的信息为");
        System.out.println("姓名     年龄     年级     专业");
        //在控制台上输出更改后的文件内容
        line.forEach(System.out::println);

        //向文件中写入” “，相当于清空文件
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(" ");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //最后一个任务就是把更改后的list集合写入文件
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (String s : line) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    void sad() throws IOException {//the meaning of "sad" is search and display hhhhhhhh
        //sad 函数实现两个功能，匹配指定的数据和输出文件中所有的数据
        String fileName = "D:\\mybyte\\fos.txt";
        List<String> line = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);//将文件中数据读入list集合中
        System.out.println("请输入你要查找的信息");
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();//定义查找的数据
        for (String s : line) {
            if (s.equals(key))//如果查到的信息与list集合中匹配，则输出
            {
                System.out.println("查到的信息为");
                System.out.println("姓名     年龄     年级     专业");
                System.out.println(s);
            }
        }
        System.out.println("文件中的所有信息为");
        System.out.println("姓名     年龄     年级     专业");
        line.forEach(System.out::println);//将list集合中数据读出
    }
}





