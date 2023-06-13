package com.revature.katieskritters.dtos.requests;

import com.revature.katieskritters.entities.Fish;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewFishRequest {
    private Fish fish;
    private int quantity;
}
