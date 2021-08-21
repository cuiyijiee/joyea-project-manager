package me.cuiyijie.joyea.pojo.request.assemblyproblem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransAssemblyProblemPageRequest extends TransAssemblyProblemRequest {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
