// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import frc.robot.Constants.CameraConstants;
import frc.robot.utils.AprilCam;
import frc.robot.utils.Retroreflective;
import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
  
  private AprilCam gridAprilCam;
  private Retroreflective gridLLCam;
  private static Camera instance;
  
  /** Creates a new Camera. */
  
  public Camera() {
    
    gridAprilCam = new AprilCam(CameraConstants.GRID_APRIL_CAM_NAME);
    gridLLCam = new Retroreflective(CameraConstants.GRID_LL_CAM);

  }

  public AprilCam getGridAprilCam(){
    return gridAprilCam;
  }

  public Retroreflective getGridLLCam(){
    return gridLLCam;
  }

  public static Camera getInstance() {
    if (instance == null) {
      instance = new Camera();
    }
    return instance;
  }
 


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
