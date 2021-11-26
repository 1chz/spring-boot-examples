package io.github.shirohoo.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Cat {

    private String name;
    private int age;
    
    /*
    @RequestBody의 경우 기본생성자가 없을 경우 하기와 같은 예외가 발생
    NestedServletException: Cannot construct instance of `io.github.shirohoo.mvc.model.Cat` (no Creators, like default constructor, exist)
     */

}
