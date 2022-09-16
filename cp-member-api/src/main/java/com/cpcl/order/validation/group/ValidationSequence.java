package com.cpcl.order.validation.group;

import javax.validation.GroupSequence;

@GroupSequence({ValidationSequence.ExistCheck.class, ValidationSequence.DataCheck.class, ValidationSequence.LogicCheck.class})
public interface ValidationSequence {
    interface ExistCheck {}
    interface DataCheck {}
    interface LogicCheck {}
}
