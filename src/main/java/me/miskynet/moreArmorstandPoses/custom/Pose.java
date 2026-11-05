package me.miskynet.moreArmorstandPoses.custom;

import me.miskynet.moreArmorstandPoses.utils.Config;
import me.miskynet.moreArmorstandPoses.utils.PDCUtils;
import me.miskynet.moreArmorstandPoses.utils.Utils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Set;

/**
 * A pose is used to change the rotation of the body parts of an {@link ArmorStand}
 * It contains six {@link EulerAngle}'s that represent each part of the {@link ArmorStand}
 * */
public class Pose {

    public static final ArrayList<Pose> poseList = new ArrayList<>();

    private final EulerAngle head, body, armRight, armLeft, legRight, legLeft;
    private final String name;
    private final Float rotation;

    /**
     * Constructor for {@link Pose}
     * @param name Name of the Pose (mostly used for display purposes)
     * @param head {@link EulerAngle} of the head
     * @param body {@link EulerAngle} of the body
     * @param armRight {@link EulerAngle} of the right arm
     * @param armLeft {@link EulerAngle} of the left arm
     * @param legRight {@link EulerAngle} of the right leg
     * @param legLeft {@link EulerAngle} of the left leg
     * */
    public Pose(String name, Float rotation, EulerAngle head, EulerAngle body, EulerAngle armRight, EulerAngle armLeft, EulerAngle legRight, EulerAngle legLeft) {
        this.name = name;
        this.rotation = rotation;
        this.head = head;
        this.body = body;
        this.armRight = armRight;
        this.armLeft = armLeft;
        this.legRight = legRight;
        this.legLeft = legLeft;
    }

    /**
     * Apply the {@link Pose} to an {@link ArmorStand}
     * @param armorStand The {@link ArmorStand} the {@link Pose} should be applied on
     * */
    public void applyPose(ArmorStand armorStand) {
        armorStand.setRotation((float) PDCUtils.getPDC(armorStand, PDCUtils.originalRotation, PersistentDataType.FLOAT) + this.rotation, 0);
        armorStand.setHeadPose(this.head);
        armorStand.setBodyPose(this.body);
        armorStand.setRightArmPose(this.armRight);
        armorStand.setLeftArmPose(this.armLeft);
        armorStand.setRightLegPose(this.legRight);
        armorStand.setLeftLegPose(this.legLeft);
    }

    /**
     * Get a pose out of the {@link #poseList} by an ID
     * @param id ID of the pose that should be looked for
     * @return {@link Pose} if it was found, null otherwise
     * */
    public static Pose getPoseByID(int id) {
        if (poseList.get(id) != null) return poseList.get(id);
        else return null;
    }

    /**
     * Get the name of a {@link Pose}
     * @return The name of the {@link Pose}
     * */
    public String getName() {
        return this.name;
    }

    /**
     * Load all poses and put them into the {@link #poseList}
     * */
    public static void setupPoses() {
        // default pose
        Pose.poseList.add(new Pose(
                "Default",
                0.0f,
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));

        // t pose
        Pose.poseList.add(new Pose(
                "T-Pose",
                0.0f,
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 90d),
                Utils.radEuler(0d, 0d, 270d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));

        // walking
        Pose.poseList.add(new Pose(
                "Walking",
                0.0f,
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(330d, 0d, 0d),
                Utils.radEuler(30d, 0d, 0d),
                Utils.radEuler(30d, 0d, 0d),
                Utils.radEuler(330d, 0d, 0d)));

        // expert
        Pose.poseList.add(new Pose(
                "Expert",
                0.0f,
                Utils.radEuler(20d, 30d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(300d, 340d, 70d),
                Utils.radEuler(0d, 350d, 0d),
                Utils.radEuler(10d, 20d, 5d),
                Utils.radEuler(355d, 355d, 355d)));

        // salute
        Pose.poseList.add(new Pose(
                "Salute",
                0.0f,
                Utils.radEuler(355d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 355d),
                Utils.radEuler(210d, 25d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));

        loadPosesFromConfig();
    }

    /**
     * @return ID of the current pose, null if no pose was found
     * */
    public static Integer getCurrentPoseID(ArmorStand armorStand) {
        if (PDCUtils.getPDC(armorStand, PDCUtils.poseID, PersistentDataType.INTEGER) != null)
            return (int) PDCUtils.getPDC(armorStand, PDCUtils.poseID, PersistentDataType.INTEGER);
        else return null;
    }

    /**
     * Load all poses from the config
     * */
    public static void loadPosesFromConfig() {

        Set<String> poses = Config.get("customPoses.yml").getConfigurationSection("").getKeys(false);

        // loop through each section (each pose)
        for (String singlePose : poses) {
            String name = (String) Config.get("customPoses.yml", singlePose + ".name");
            Float rotation = Float.parseFloat(Config.get("customPoses.yml", singlePose + ".rotation").toString());
            EulerAngle head = getEulerAngles(singlePose, "head");
            EulerAngle body = getEulerAngles(singlePose, "body");
            EulerAngle armRight = getEulerAngles(singlePose, "armRight");
            EulerAngle armLeft = getEulerAngles(singlePose, "armLeft");
            EulerAngle legRight = getEulerAngles(singlePose, "legRight");
            EulerAngle legLeft = getEulerAngles(singlePose, "legLeft");

            Pose pose = new Pose(name, rotation, head, body, armRight, armLeft, legRight, legLeft);

            poseList.add(pose);
        }
    }

    /**
     * Helper method for {@link #loadPosesFromConfig()}.
     * Input a configKey and a section to get a {@link EulerAngle}
     * (The targeted section HAS TO BE an integer list)
     * @param configKey Key of the config section
     * @param section Key of the config section key
     * @return There values in an {@link EulerAngle}
     * */
    private static EulerAngle getEulerAngles(String configKey, String section) {
        ArrayList<Integer> valueList = (ArrayList<Integer>) Config.get("customPoses.yml").getIntegerList(configKey + "." + section);

        return Utils.radEuler(Double.parseDouble(valueList.get(0).toString()),
                Double.parseDouble(valueList.get(1).toString()),
                Double.parseDouble(valueList.get(2).toString()));
    }


//    // get each child element (name, head, body, ...)
//            for (String element : elements) {
//
//        Bukkit.getLogger().info("current element: " + element);
//
//        if (!(element.equals("name"))) {
//            List<Integer> values = (List<Integer>) Config.get("customPoses.yml").getList(singlePose + "." + element);;
//
//            EulerAngle angle = Utils.radEuler(Double.parseDouble(values.get(0).toString()),
//                    Double.parseDouble(values.get(1).toString()),
//                    Double.parseDouble(values.get(2).toString()));
//        }
//    }

}
