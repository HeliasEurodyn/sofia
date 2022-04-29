package com.crm.sofia.dto.sofia.data_transfer;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.sofia.appview.AppViewDTO;
import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.custom_query.CustomQueryDTO;
import com.crm.sofia.dto.sofia.dashboard.DashboardDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.dto.sofia.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.dto.sofia.info_card.InfoCardDTO;
import com.crm.sofia.dto.sofia.language.LanguageDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.dto.sofia.menu.MenuDTO;
import com.crm.sofia.dto.sofia.report.ReportDTO;
import com.crm.sofia.dto.sofia.search.SearchDTO;
import com.crm.sofia.dto.sofia.table.TableDTO;
import com.crm.sofia.dto.sofia.user.RoleDTO;
import com.crm.sofia.dto.sofia.user.UserDTO;
import com.crm.sofia.dto.sofia.view.ViewDTO;
import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.model.sofia.html_dashboard.HtmlDashboard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class DataTransferDTO extends BaseDTO {

    private String name;

    private String description;

    private String query;

    private Integer currentVersion;

    List<String> formIds;
    List<String> listIds;
    List<String> tableIds;
    List<String> viewIds;
    List<String> appViewIds;

    List<FormDTO> forms;
    List<ListDTO> lists;
    List<TableDTO> tables;
    List<ViewDTO> views;
    List<AppViewDTO> appViews;

    List<String> menuIds;
    List<String> componentIds;
    List<String> chartIds;
    List<String> infoCardIds;
    List<String> htmlPartIds;
    List<String> dashboardIds;
    List<String> reportIds;
    List<String> xlsImportIds;
    List<String> searchIds;
    List<String> customQueryIds;
    List<String> languageIds;
    List<String> roleIds;
    List<String> userIds;

    List<MenuDTO> menus;
    List<ComponentDTO> components;
    List<ChartDTO> charts;
    List<InfoCardDTO> infoCards;
    List<HtmlDashboardDTO> htmlParts;
    List<DashboardDTO> dashboards;
    List<ReportDTO> reports;
    List<XlsImportDTO> xlsImports;
    List<SearchDTO> searchs;
    List<CustomQueryDTO> customQuerys;
    List<LanguageDTO> languages;
    List<RoleDTO> roles;
    List<UserDTO> users;

}
