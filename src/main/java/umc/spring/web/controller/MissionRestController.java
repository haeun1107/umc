package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {

    public final MissionCommandService missionCommandService;

    @PostMapping("/add")
    public ApiResponse<MissionResponseDTO> addMission(@RequestBody @Valid MissionRequestDTO missionRequestDTO) {
        return ApiResponse.onSuccess(missionCommandService.addMission(missionRequestDTO));
    }
}
