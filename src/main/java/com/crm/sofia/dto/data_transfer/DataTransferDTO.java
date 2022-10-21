package com.crm.sofia.dto.data_transfer;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.custom_query.CustomQueryDTO;
import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.language.LanguageDTO;
import com.crm.sofia.dto.list.base.ListDTO;
import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.user.RoleDTO;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.xls_import.XlsImportDTO;
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
