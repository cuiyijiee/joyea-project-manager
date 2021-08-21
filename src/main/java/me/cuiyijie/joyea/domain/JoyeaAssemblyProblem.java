package me.cuiyijie.joyea.domain;

import lombok.Data;

@Data
public class JoyeaAssemblyProblem {

    private String problemId;

    private String projectNumber;
    private String projectName;
    private String productLine;
    private String equipmentName;
    private String componentName;
    private String problemDesc;
    private String feedbackPerson;
    private String problemCategory;
    private String problemReason;
    private String problemSolution;
    private boolean isInvolved;
    private String workPerson;
    private String solve;
    private boolean isAssembly;
    private String assemblySolution;

    private JoyeaAssemblyProblemSettings settings;

}
