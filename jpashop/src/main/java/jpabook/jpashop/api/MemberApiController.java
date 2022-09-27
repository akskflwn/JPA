package jpabook.jpashop.api;

import jpabook.jpashop.Service.MemberService;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result findMembers() {
        List<Member> members = memberService.findMembers();
        List<MemberDTO> collect = members.stream()
                .map(m -> new MemberDTO(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int cnt;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDTO {
        String name;
    }
}
