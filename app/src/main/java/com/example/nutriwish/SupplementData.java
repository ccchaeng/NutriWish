package com.example.nutriwish;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplementData {
    public static Map<String, List<Supplement>> getCategorySupplements() {
        Map<String, List<Supplement>> categorySupplements = new HashMap<>();

        // 면역 강화 카테고리
        List<Supplement> immunitySupplements = new ArrayList<>();
        immunitySupplements.add(new Supplement("비타민 C", "비타민 C는 면역력을 강화하고 항산화 효과를 제공합니다.", "하루 500mg에서 1000mg을 물과 함께 섭취하세요.", "고용량 섭취 시 속쓰림이나 소화 장애가 발생할 수 있습니다."));
        immunitySupplements.add(new Supplement("에키나시아", "에키나시아는 면역 세포 활성화를 도와 감기 예방에 효과적입니다.", "하루 1-2 캡슐을 물과 함께 섭취하세요.", "임신 중인 경우 복용 전 전문가와 상담하세요."));
        immunitySupplements.add(new Supplement("프로폴리스", "프로폴리스는 항균 및 항염 작용으로 면역력을 강화합니다.", "하루 1-2 스프레이를 입안에 뿌리거나 캡슐을 섭취하세요.", "벌 알레르기가 있는 사람은 주의가 필요합니다."));
        categorySupplements.put("면역 강화", immunitySupplements);


        // 피로 회복 카테고리
        List<Supplement> fatigueSupplements = new ArrayList<>();
        fatigueSupplements.add(new Supplement("홍삼", "홍삼은 에너지 증진과 피로 회복에 도움을 줍니다.", "하루 1-2포를 물과 함께 섭취하세요.", "고혈압 환자는 섭취 전 의사와 상담하세요."));
        fatigueSupplements.add(new Supplement("비타민 B 컴플렉스", "비타민 B 컴플렉스는 신진대사를 촉진하고 피로를 줄여줍니다.", "하루 1정을 식사 후 섭취하세요.", "빈 속에 복용 시 위장 장애가 있을 수 있습니다."));
        fatigueSupplements.add(new Supplement("코엔자임 Q10", "코엔자임 Q10은 세포 에너지 생산을 돕고 피로 회복에 기여합니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "혈압 약 복용 시 의사와 상담하세요."));
        categorySupplements.put("피로 회복", fatigueSupplements);

        // 관절 건강 카테고리
        List<Supplement> jointSupplements = new ArrayList<>();
        jointSupplements.add(new Supplement("글루코사민", "글루코사민은 관절의 유연성을 유지하고 연골을 보호합니다.", "하루 1500mg을 식사와 함께 섭취하세요.", "해산물 알레르기가 있는 경우 주의하세요."));
        jointSupplements.add(new Supplement("콘드로이틴", "콘드로이틴은 연골 건강을 개선하고 관절 통증을 줄입니다.", "하루 1200mg을 식사와 함께 섭취하세요.", "혈액 응고 장애가 있는 경우 사용을 피하세요."));
        jointSupplements.add(new Supplement("MSM", "MSM은 염증을 줄이고 관절 통증을 완화시킵니다.", "하루 1000mg을 물과 함께 섭취하세요.", "장기 복용 시 간 기능 모니터링이 필요할 수 있습니다."));
        categorySupplements.put("관절 건강", jointSupplements);

        // 소화 개선 카테고리
        List<Supplement> digestionSupplements = new ArrayList<>();
        digestionSupplements.add(new Supplement("프로바이오틱스", "프로바이오틱스는 장 건강을 개선하고 소화를 돕습니다.", "하루 1캡슐을 아침 식사와 함께 섭취하세요.", "냉장 보관이 필요할 수 있습니다."));
        digestionSupplements.add(new Supplement("디제스타제", "디제스타제는 소화 효소를 보충하여 소화를 원활하게 합니다.", "식사 전에 하루 1정을 섭취하세요.", "효소 알레르기가 있는 경우 주의하세요."));
        digestionSupplements.add(new Supplement("파파야 효소", "파파야 효소는 단백질 소화를 돕고 소화 불량을 완화합니다.", "식사 후 하루 1-2정을 씹어 섭취하세요.", "알레르기 반응이 있을 수 있습니다."));
        categorySupplements.put("소화 개선", digestionSupplements);

        // 피부 & 모발 건강 카테고리
        List<Supplement> skinHairSupplements = new ArrayList<>();
        skinHairSupplements.add(new Supplement("콜라겐", "콜라겐은 피부 탄력과 수분을 유지하며, 모발 건강을 개선합니다.", "하루 1포를 물에 섞어 섭취하세요.", "임신부는 섭취 전 전문가와 상담하세요."));
        skinHairSupplements.add(new Supplement("비오틴", "비오틴은 모발과 손톱의 건강을 유지하고, 피부에 좋은 영향을 줍니다.", "하루 1정을 식사와 함께 섭취하세요.", "지속적인 복용이 필요합니다."));
        skinHairSupplements.add(new Supplement("히알루론산", "히알루론산은 피부의 수분을 유지하고 보습력을 향상시킵니다.", "하루 1정을 물과 함께 섭취하세요.", "알레르기 반응을 주의하세요."));
        categorySupplements.put("피부 & 모발 건강", skinHairSupplements);

        // 두뇌 & 집중력 강화 카테고리
        List<Supplement> brainFocusSupplements = new ArrayList<>();
        brainFocusSupplements.add(new Supplement("오메가-3", "오메가-3는 두뇌 기능을 지원하고 기억력 개선에 도움을 줍니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "고용량 섭취 시 혈액 응고에 주의하세요."));
        brainFocusSupplements.add(new Supplement("기억력 개선 허브", "기억력 개선 허브는 두뇌 활성화에 도움을 줍니다.", "하루 1캡슐을 아침에 섭취하세요.", "허브 알레르기가 있는 경우 주의하세요."));
        brainFocusSupplements.add(new Supplement("레시틴", "레시틴은 기억력과 집중력 향상에 기여합니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "지속적으로 섭취해야 효과가 있습니다."));
        categorySupplements.put("두뇌 & 집중력 강화", brainFocusSupplements);

        // 눈 건강 카테고리
        List<Supplement> eyeSupplements = new ArrayList<>();
        eyeSupplements.add(new Supplement("루테인", "루테인은 눈의 망막을 보호하고 시력을 개선합니다.", "하루 1정을 식사와 함께 섭취하세요.", "과량 섭취 시 황변증이 나타날 수 있습니다."));
        eyeSupplements.add(new Supplement("비타민 A", "비타민 A는 야맹증 예방과 시력 보호에 도움을 줍니다.", "하루 1정을 식사와 함께 섭취하세요.", "지나친 섭취는 독성을 유발할 수 있습니다."));
        eyeSupplements.add(new Supplement("제아잔틴", "제아잔틴은 눈의 건강을 유지하고 블루라이트로부터 보호합니다.", "하루 1정을 식사 후 섭취하세요.", "고용량 섭취는 피하세요."));
        categorySupplements.put("눈 건강", eyeSupplements);

        // 심장 & 혈관 건강 카테고리
        List<Supplement> heartVascularSupplements = new ArrayList<>();
        heartVascularSupplements.add(new Supplement("코엔자임 Q10", "코엔자임 Q10은 심장 기능을 지원하고 에너지 생성을 돕습니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "혈압 약과 함께 복용 시 주의가 필요합니다."));
        heartVascularSupplements.add(new Supplement("오메가-3", "오메가-3는 혈중 콜레스테롤 수치를 낮추고 심장 건강을 개선합니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "고용량 섭취는 혈액 응고에 영향을 미칠 수 있습니다."));
        heartVascularSupplements.add(new Supplement("레스베라트롤", "레스베라트롤은 혈관 건강을 유지하고 항산화 작용을 합니다.", "하루 1정을 물과 함께 섭취하세요.", "임산부는 섭취 전 상담이 필요합니다."));
        categorySupplements.put("심장 & 혈관 건강", heartVascularSupplements);

        // 다이어트 & 체중 관리 카테고리
        List<Supplement> dietWeightSupplements = new ArrayList<>();
        dietWeightSupplements.add(new Supplement("가르시니아", "가르시니아는 지방 합성을 억제하고 체중 감소에 도움을 줍니다.", "하루 1-2정을 식사 30분 전에 섭취하세요.", "공복에 섭취 시 위장 장애가 발생할 수 있습니다."));
        dietWeightSupplements.add(new Supplement("녹차 추출물", "녹차 추출물은 체지방 감소와 항산화 효과를 제공합니다.", "하루 1정을 식사와 함께 섭취하세요.", "카페인 민감자는 주의가 필요합니다."));
        dietWeightSupplements.add(new Supplement("CLA", "CLA는 체지방 감소와 근육 유지에 도움을 줍니다.", "하루 1-2 캡슐을 식사와 함께 섭취하세요.", "과다 복용 시 소화 불량이 있을 수 있습니다."));
        categorySupplements.put("다이어트 & 체중 관리", dietWeightSupplements);

        // 숙면 & 스트레스 완화 카테고리
        List<Supplement> sleepStressSupplements = new ArrayList<>();
        sleepStressSupplements.add(new Supplement("멜라토닌", "멜라토닌은 수면 사이클을 조절하여 숙면을 유도합니다.", "잠들기 30분 전에 1정을 섭취하세요.", "장기 복용은 피하는 것이 좋습니다."));
        sleepStressSupplements.add(new Supplement("마그네슘", "마그네슘은 신경 이완과 근육 이완에 도움을 줍니다.", "잠들기 전에 1정을 물과 함께 섭취하세요.", "과량 섭취 시 설사가 발생할 수 있습니다."));
        sleepStressSupplements.add(new Supplement("라벤더 오일", "라벤더 오일은 스트레스를 줄이고 편안한 수면을 유도합니다.", "잠들기 전에 몇 방울을 베개에 떨어뜨리세요.", "피부에 직접 사용 시 자극이 있을 수 있습니다."));
        categorySupplements.put("숙면 & 스트레스 완화", sleepStressSupplements);

        return categorySupplements;
    }
}
