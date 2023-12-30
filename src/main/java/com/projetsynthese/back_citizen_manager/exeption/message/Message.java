package com.projetsynthese.back_citizen_manager.exeption.message;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Message {
    String message;
    Integer code;
}
