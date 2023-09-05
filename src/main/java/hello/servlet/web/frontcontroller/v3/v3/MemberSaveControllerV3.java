package hello.servlet.web.frontcontroller.v3.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> map) {
        String username = map.get("username");
        int age = Integer.parseInt(map.get("age"));
        Member member= new Member(username,age);
        System.out.println("member = " + member);

       memberRepository.save(member);
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
