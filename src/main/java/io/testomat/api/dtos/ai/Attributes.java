package io.testomat.api.dtos.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {

    @JsonProperty("environments")
    private Object environments;

    @JsonProperty("company-slug")
    private Object companySlug;

    @JsonProperty("subscription")
    private Object subscription;

    @JsonProperty("title")
    private String title;

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

    @JsonProperty("status")
    private String status;

}