package com.cpcl.order.dto;

import com.cpcl.order.validation.group.ValidationSequence;
import com.cpcl.order.validation.group.ValidationSequence.DataCheck;
import com.cpcl.order.validation.group.ValidationSequence.ExistCheck;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Validated(ValidationSequence.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderShipping {

    @NotBlank(groups = ExistCheck.class,
            message = "수령인 이름이 없습니다.")
    @Size(min = 3, max = 20,
            groups = DataCheck.class,
            message = "이름 길이는 최소 3자 부터 20자 까지입니다.")
    private String recipientName;

    @NotBlank(groups = ExistCheck.class,
            message = "수령인 핸드폰번호가 없습니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$",
            groups = DataCheck.class,
            message = "핸드폰 번호 형식이 아닙니다.")
    private String recipientPhone;

    @NotBlank(groups = ExistCheck.class,
            message = "수령 우편번호가 없습니다.")
    @Size(min = 5, max = 5,
            groups = DataCheck.class,
            message = "우편번호는 5자리 이어야 합니다.")
    private String zipCode;

    @NotBlank(groups = ExistCheck.class,
            message = "주소1이 없습니다.")
    @Size(max = 100,
            groups = DataCheck.class,
            message = "주소1는 100자리 이하입니다.")
    private String address1;

    @NotBlank(groups = ExistCheck.class,
            message = "주소2가 없습니다.")
    @Size(max = 100,
            groups = DataCheck.class,
            message = "주소2는 100자리 이하입니다.")
    private String address2;
}
