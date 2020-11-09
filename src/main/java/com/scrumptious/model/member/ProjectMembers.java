package com.scrumptious.model.member;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.scrumptious.storage.JsonableArray;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ProjectMembers implements JsonableArray {

    private ArrayList<Member> memberList;

    public ProjectMembers() {
        memberList = new ArrayList<>(100);
    }

    public int size() {
        return memberList.size();
    }

    public ArrayList<Member> getAllMembers() {
        return this.memberList;
    }

    /**Return required member if present in the projectMember.
     * @param userid Id of the member added
     * @return Member object if found, else null
     */
    public Member getMember(String userid) {
        for (Member mem : memberList) {
            if (mem.getUserId().equals(userid)) {
                return mem;
            }
        }
        return null;
    }

    public void addMember(Member m) {
        memberList.add(m);
    }

    //To check if a project contains a member
    public boolean containMember(Member member) {
        return memberList.contains(member);
    }

    public void removeMember(Member member) {
        memberList.remove(member);
    }

    public String toString() {
        StringBuilder membersString = new StringBuilder();
        membersString.append("[Members:");
        for (Member mem : memberList) {
            membersString.append(String.format(" %s", mem.getUserId()));
        }
        membersString.append("]\n");
        return membersString.toString();
    }

    @Override
    public String toJson() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonArray jMemberList = new JsonArray(memberList);
        jMemberList.toJson(writer);
    }

    @Override
    public void fromJson(JsonArray jsonArr) {
        for (Object o : jsonArr) {
            Member member = new Member();
            member.fromJson((JsonObject) o);
            memberList.add(member);
        }
    }
}
