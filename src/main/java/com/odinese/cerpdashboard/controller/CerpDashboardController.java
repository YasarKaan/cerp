package com.odinese.cerpdashboard.controller;

import com.odinese.cerpdashboard.service.CerpDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CerpDashboardController {

    private CerpDashboardService cerpDashboardService;

    @GetMapping("/ceph/health")
    public String getMinimalHealthStatus(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String url) {
        return cerpDashboardService.getMinimalHealthStatus(username, password, url);
    }

    @GetMapping("/ceph/health/diff")
    public String getHealthDiff(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String url) {
        return cerpDashboardService.getHealthDiff(username, password, url);
    }
}
