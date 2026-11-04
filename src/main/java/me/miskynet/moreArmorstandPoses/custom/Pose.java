package me.miskynet.moreArmorstandPoses.custom;

import me.miskynet.moreArmorstandPoses.utils.PDCUtils;
import me.miskynet.moreArmorstandPoses.utils.Utils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;

/**
 * A pose is used to change the rotation of the body parts of an {@link ArmorStand}
 * It contains six {@link EulerAngle}'s that represent each part of the {@link ArmorStand}
 * */
public class Pose {

    public static final ArrayList<Pose> poseList = new ArrayList<>();

    private final EulerAngle head, body, armRight, armLeft, legRight, legLeft;
    private final String name;

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
    public Pose(String name, EulerAngle head, EulerAngle body, EulerAngle armRight, EulerAngle armLeft, EulerAngle legRight, EulerAngle legLeft) {
        this.name = name;
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
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));

        // t pose
        Pose.poseList.add(new Pose(
                "T-Pose",
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 90d),
                Utils.radEuler(0d, 0d, 270d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));

        // walking
        Pose.poseList.add(new Pose(
                "Walking",
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(330d, 0d, 0d),
                Utils.radEuler(30d, 0d, 0d),
                Utils.radEuler(30d, 0d, 0d),
                Utils.radEuler(330d, 0d, 0d)));

        // expert
        Pose.poseList.add(new Pose(
                "Expert",
                Utils.radEuler(20d, 30d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(300d, 340d, 70d),
                Utils.radEuler(0d, 350d, 0d),
                Utils.radEuler(10d, 20d, 5d),
                Utils.radEuler(355d, 355d, 355d)));

        // salute
        Pose.poseList.add(new Pose(
                "Salute",
                Utils.radEuler(355d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 355d),
                Utils.radEuler(210d, 25d, 0d),
                Utils.radEuler(0d, 0d, 0d),
                Utils.radEuler(0d, 0d, 0d)));
    }

    /**
     * @return ID of the current pose, null if no pose was found
     * */
    public static Integer getCurrentPoseID(ArmorStand armorStand) {
        if (PDCUtils.getPDC(armorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER) != null)
            return (int) PDCUtils.getPDC(armorStand, PDCUtils.armorStandPoseID, PersistentDataType.INTEGER);
        else return null;
    }
}
