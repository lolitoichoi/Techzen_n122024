package com.techzen.academy_n1224;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DictionaryController {
    private final Map<String, String> dictionary = Map.ofEntries(
            Map.entry("một", "One"),
            Map.entry("hai", "Two"),
            Map.entry("ba", "Three"),
            Map.entry("bốn", "Four"),
            Map.entry("năm", "Five"),
            Map.entry("con vịt", "Duck"),
            Map.entry("con chó", "Dog"),
            Map.entry("con mèo", "Cat"),
            Map.entry("con chim", "Bird"),
            Map.entry("máy bay", "Plane"),
            Map.entry("tàu hỏa", "Train")
    );

    @GetMapping("/dictionary")
    public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String dictionaryName) {
        // Loại bỏ khoảng trống ở đầu và cuối, chuyển đổi thành chữ thường
        String key = dictionaryName.trim().toLowerCase();

        // Kiểm tra xem từ khóa có trong từ điển không
        String dich = dictionary.get(key);

        if (dich != null) {
            return ResponseEntity.ok(dich);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dictionary not found");
    }
}