package entity.vacancy;

import java.util.ArrayList;
public class KeySkills {
    private ArrayList<String> keySkills = new ArrayList<>();

    public KeySkills(String... skills) {
        for (String skill : skills) {
            this.keySkills.add(skill);
        }
    }

    public String getKeySkills() {
        StringBuilder keySkill = new StringBuilder();
        keySkill.append("Ключевые навыки:\n");
        for (String skill : this.keySkills) {
            keySkill
                    .append(" -> ")
                    .append(skill)
                    .append("\n");
        }
        return keySkill.toString();
    }

}
