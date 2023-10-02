package io.testomat.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {

    @JsonProperty("environments")
    private Object environments;

    @JsonProperty("company-slug")
    private Object companySlug;

    @JsonProperty("subscription")
    private Object subscription;

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    @JsonProperty("demo")
    private Boolean demo;

    @JsonProperty("branch")
    private Object branch;

    @JsonProperty("record-url")
    private String recordUrl;

    @JsonProperty("features")
    private Object features;

    @JsonProperty("api-key")
    private String apiKey;

    @JsonProperty("import-progress")
    private Object importProgress;

    @JsonProperty("created-at")
    private String createdAt;

    @JsonProperty("company")
    private Object company;

    @JsonProperty("versions-max-days")
    private Integer versionsMaxDays;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("tests-count")
    private Integer testsCount;

    @JsonProperty("jira-count")
    private Object jiraCount;

    @JsonProperty("runs-settings")
    private Object runsSettings;

    @JsonProperty("jira")
    private Object jira;
    @JsonProperty("jira-issues")
    private Object jiraIssues;

    @JsonProperty("automated-run-timeout")
    private Object automatedRunTimeout;

    @JsonProperty("github")
    private Object github;

    @JsonProperty("run-replies")
    private Object runReplies;

    @JsonProperty("avatar")
    private Object avatar;

    @JsonProperty("artifacts-settings")
    private ArtifactsSettings artifactsSettings;

    @JsonProperty("url")
    private String url;

    @JsonProperty("manual-tag-settings")
    private Object manualTagSettings;

    @JsonProperty("ci-settings")
    private Object ciSettings;

    @JsonProperty("has-living-docs")
    private Boolean hasLivingDocs;

    @JsonProperty("framework")
    private Object framework;

    @JsonProperty("testomatio-url")
    private String testomatioUrl;

    @JsonProperty("living-doc-url")
    private String livingDocUrl;

    @JsonProperty("issue-settings")
    private Object issueSettings;

    @JsonProperty("filtered-tests")
    private Object filteredTests;

    @JsonProperty("file")
    private Object file;

    @JsonProperty("notes")
    private Object notes;


    @JsonProperty("assigned-to")
    private Object assignedTo;

    @JsonProperty("to-url")
    private String toUrl;

    @JsonProperty("position")
    private int position;

    @JsonProperty("is-root")
    private boolean isRoot;
    @JsonProperty("path")
    private Object path;

    @JsonProperty("public-title")
    private String publicTitle;
    @JsonProperty("parent-id")
    private String parentId;

    @JsonProperty("updated-at")
    private String updatedAt;

    @JsonProperty("code")
    private Object code;

    @JsonProperty("sync")
    private boolean sync;

    @JsonProperty("file-type")
    private String fileType;

    @JsonProperty("test-count")
    private int testCount;


    @JsonProperty("emoji")
    private Object emoji;

    @JsonProperty("is-branched")
    private boolean isBranched;

    @JsonProperty("is-detail")
    private boolean isDetail;


    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("issues")
    private List<String> issues;

    @JsonProperty("status")
    private String status;

    public static class ArtifactsSettings {

        @JsonProperty("presign")
        private Boolean presign;

    }

}