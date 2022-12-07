package com.company;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Scanner scan = new Scanner(System.in);

        System.out.println("Submit Image URL:  ");
        String userSubmittedImg = scan.nextLine();

        BufferedImage image = ImageIO.read(new URL(userSubmittedImg));
        ImageIO.write(image , "png", new File("C:\\Users\\Mike\\IdeaProjects\\FaceDetection\\images\\userImg.png"));


        String imgFile = "images/userImg.png";
        Mat src = Imgcodecs.imread(imgFile);

        String xmlFile = "xml/lbpcascade_frontalface.xml";
        CascadeClassifier cc = new CascadeClassifier(xmlFile);

        MatOfRect faceDetection = new MatOfRect();
        cc.detectMultiScale(src, faceDetection);
        System.out.println("Detected Faces: " + faceDetection.toArray().length);

        for (Rect rect : faceDetection.toArray()){
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,0,255), 3);
        }

        Imgcodecs.imwrite("images/userImg_out.png", src);
        System.out.println("Image Detection Finished");
    }
}
