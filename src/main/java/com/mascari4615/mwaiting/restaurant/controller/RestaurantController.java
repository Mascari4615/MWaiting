package com.mascari4615.mwaiting.restaurant.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mascari4615.mwaiting.user.repository.UserRepository;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantDTO;
import com.mascari4615.mwaiting.restaurant.controller.dto.RestaurantRegisterRequest;
import com.mascari4615.mwaiting.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// @RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserRepository userRepository;

    // 회원가입 페이지 출력 요청
    @GetMapping("/restaurant/register")
    public String registerRestaurantForm() {
        return "restaurant-register";
    }

    @PostMapping("/restaurant/register")
    public String registerRestaurant(@SessionAttribute(name = "userName", required = false) String userName, @ModelAttribute RestaurantRegisterRequest restaurantRegisterRequest) {
        System.out.println("RestaurantController.save");
        System.out.println("RestaurantRegisterRequest = " + restaurantRegisterRequest);
        System.out.println("userName = " + userName);

        Optional<User> userEntity = userRepository.findByEmail(userName);
        User user = userEntity.get();
        System.out.println("userEntity = " + user);
        restaurantService.save(restaurantRegisterRequest, user);

        return "index";
    }

    @GetMapping("/restaurant/list")
    public String findAll(Model model) // 스프링에서 제공하는 객체 표현
    {
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("restaurantList", restaurantDTOList);
        return "restaurant-list";
    }

    @GetMapping("/restaurant/{id}")
    public String findById(@PathVariable Long id, Model model) {
        RestaurantDTO restaurantDTO = restaurantService.findById(id);
        model.addAttribute("restaurant", restaurantDTO);
        return "restaurant-detail";
    }

    //HACK
    @GetMapping("/restaurant/qr/{id}")
    public ResponseEntity<byte[]> getUserQR(@PathVariable Long id) throws WriterException, IOException {
        // QR 정보
        int width = 200;
        int height = 200;
        String url = "localhost:8080/restaurant-home/" + id;

        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(out.toByteArray());

        } catch (Exception e) {
            log.warn("QR Code OutputStream 도중 Excpetion 발생, {}", e.getMessage());
        }

        return null;
    }

    @GetMapping("/restaurant-home/{id}")
    public String restaurantHome(@PathVariable Long id, Model model) {
        RestaurantDTO restaurantDTO = restaurantService.findById(id);
        model.addAttribute("restaurant", restaurantDTO);
        return "restaurant-home";
    }
}