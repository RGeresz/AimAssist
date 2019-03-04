package ProjektPZ.pattern;

import com.icafe4j.image.gif.FrameReader;
import com.icafe4j.image.gif.GIFFrame;

import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "pattern", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Pattern implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column(unique = true)
    private String weaponName;
    @OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<dMousePosition> dMousePositions;

    public Pattern() {
    }

    public Pattern(String weaponName, String filePath, int rpm) throws Exception {

        dMousePositions = new LinkedList<>();
        this.weaponName = weaponName;

        FileInputStream is = new FileInputStream(filePath);
        FrameReader frameReader = new FrameReader();
        GIFFrame gifFrame = frameReader.getGIFFrameEx(is);
        LinkedList<BufferedImage> gifFrames = new LinkedList<>();

        for (int i = 0; gifFrame != null; gifFrame = frameReader.getGIFFrameEx(is), i++) {
            gifFrames.add(gifFrame.getFrame());
        }
        is.close();

        LinkedList<BufferedImage> preperedImages = new LinkedList<>();
        preperedImages.add(gifFrames.get(0));

        for (int i = 0; i < gifFrames.size() - 1; i++) {
            preperedImages.add(prepareImage(gifFrames.get(i), gifFrames.get(i + 1)));
        }

        LinkedList<BufferedImage> differenceImages = new LinkedList<>();

        for (int i = 0; i < preperedImages.size() - 1; i++) {
            differenceImages.add(getDifferenceImage(preperedImages.get(i), preperedImages.get(i + 1)));
        }

        for (int i = 0; i < differenceImages.size() - 2; i++) {
            dMousePositions.add(new dMousePosition((int) (getPoint(differenceImages.get(i)).getX() - getPoint(differenceImages.get(i + 1)).getX()),
                    (int) (getPoint(differenceImages.get(i)).getY() - getPoint(differenceImages.get(i + 1)).getY()), (int) ((double) 60 / rpm * 1000), this));
        }

    }


    public Pattern(String weaponName, LinkedList<dMousePosition> dMousePositions) {
        this.weaponName = weaponName;
        this.dMousePositions = dMousePositions;
        for (dMousePosition p : dMousePositions) {
            p.setPattern(this);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        if (!weaponName.isEmpty()) {
            this.weaponName = weaponName;
        } else
            throw new IllegalArgumentException("Pattern name can't be empty!");
    }


    public List<dMousePosition> getDMousePositions() {
        return dMousePositions;
    }

    public void setDMousePositions(LinkedList<dMousePosition> dMousePositions) {
        this.dMousePositions = dMousePositions;
    }

    public static LinkedList<dMousePosition> optimize(LinkedList<dMousePosition> pointsInTime) {
        LinkedList<dMousePosition> rtn = new LinkedList<>();
        rtn.add(new dMousePosition(0, 0, 0));
        for (dMousePosition p : pointsInTime) {
            if (rtn.get(rtn.size() - 1).getDx() == 0 && rtn.get(rtn.size() - 1).getDy() == 0) {
                rtn.set(rtn.size() - 1, rtn.get(rtn.size() - 1).add(p));
            } else {
                rtn.add(p);
            }
        }
        return rtn;
    }

    public void reverse() {
        for (dMousePosition dmp : dMousePositions) {
            dmp.setDx(-dmp.getDx());
            dmp.setDy(-dmp.getDy());
        }
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "weaponName='" + weaponName + '\'' +
                ", pointsInTime=" + dMousePositions +
                '}';
    }

    public static Point getPoint(BufferedImage img) {

        int minx = img.getWidth(), miny = img.getHeight(), maxx = 0, maxy = 0;

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (img.getRGB(j, i) == Color.RED.getRGB()) {
                    if (minx > j) minx = j;
                    if (miny > i) miny = i;
                    if (maxx < j) maxx = j;
                    if (maxy < i) maxy = i;
                }
            }
        }

        return new Point((minx + maxx) / 2, (miny + maxy) / 2);
    }

    public static BufferedImage prepareImage(BufferedImage img1, BufferedImage img2) {

        int width = img1.getWidth();
        int height = img1.getHeight();

        BufferedImage outImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                colorImpactsToRed(img1, i, j);
                colorImpactsToRed(img2, i, j);
                if (img1.getRGB(j, i) == Color.RED.getRGB() || img2.getRGB(j, i) == Color.RED.getRGB()) {
                    outImg.setRGB(j, i, Color.RED.getRGB());
                } else
                    outImg.setRGB(j, i, Color.BLACK.getRGB());
            }
        }

        return outImg;
    }

    private static void colorImpactsToRed(BufferedImage img, int i, int j) {
        if ((img.getRGB(j, i) & 0xFF0000) == Integer.parseInt("FF0000", 16)) {
            img.setRGB(j, i, Color.RED.getRGB());
        } else
            img.setRGB(j, i, Color.BLACK.getRGB());
    }

    private BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2){
        BufferedImage outImg = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < img1.getHeight(); i++) {
            for (int j = 0; j < img1.getWidth(); j++) {
                if (img1.getRGB(j, i) != img2.getRGB(j, i)) {
                    outImg.setRGB(j, i, Color.RED.getRGB());
                } else
                    outImg.setRGB(j, i, Color.BLACK.getRGB());
            }
        }

        return outImg;
    }


}
