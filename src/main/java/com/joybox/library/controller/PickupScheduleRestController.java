package com.joybox.library.controller;

import com.joybox.library.model.Book;
import com.joybox.library.model.PickupSchedule;
import com.joybox.library.model.PickupScheduleResponse;
import com.joybox.library.model.PickupScheduleSpec;
import com.joybox.library.model.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.joybox.library.controller.BookRestController.books;

@RestController
@RequestMapping("/pickupSchedule")
public class PickupScheduleRestController {
  protected List<PickupScheduleResponse> pickupSchedules = new ArrayList<>();

  @GetMapping("/")
  public String root() {
    return "PickupScheduleRestController is running";
  }

  @GetMapping("/list")
  public List<PickupScheduleResponse> getPickupSchedules() {
    return pickupSchedules;
  }

  @PostMapping("/create")
  public ResponseEntity<CommonResponse> addPickupSchedules(@RequestBody PickupScheduleSpec spec) {
    Book book = books.stream().filter(b -> b.getTitle().equals(spec.getBookTitle())).findFirst().orElse(null);
    if (book == null) {
      return new ResponseEntity<>(new CommonResponse(false, "The book is not found"), HttpStatus.NOT_FOUND);
    }

    pickupSchedules.add(new PickupScheduleResponse(
      book,
      spec.getPickupDate(),
      spec.getPickupTime()
    ));

    return new ResponseEntity<>(new CommonResponse(true, ""), HttpStatus.CREATED);
  }
}
