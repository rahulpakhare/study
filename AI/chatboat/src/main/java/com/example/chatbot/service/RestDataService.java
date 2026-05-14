package com.example.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestDataService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public RestDataService(@Value("${app.rest.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public String fetchData(String question) {
        String lowerQuestion = question.toLowerCase();

        try {
            if (lowerQuestion.contains("initiative") ) {
                System.out.println("Inside initiative");
                String response = """
                        "work_id","application","application_version","commit_date_time","cover_ins_key","covered_count","covered_count_open","covered_count_unsatisfied","create_date_time","create_op_name","create_operator","create_system_id","current_stage","current_stage_label","external_system_update_count","flow_count","ins_name","obj_class","save_date_time","update_date_time","update_op_name","update_operator","update_system_id","urgency_work","ack_timestamp","age_from_date","charge_amount","charge_to","contact_channel","contact_type","cus_level","customer","customer_enterprise","customer_name","customer_org","customer_satisfied_timestamp","description","effort_actual","effort_estimate","effort_estimate_timestamp","elapsed_customer_ack","elapsed_customer_unsatisfied","elapsed_past_deadline","elapsed_past_goal","elapsed_status_new","elapsed_statu_sopen","elapsed_status_pending","event_id","folder_type","id","label","orig_division","ori_gorg","orig_org_unit","orig_user_division","orig_user_id","orig_user_workgroup","owner_division","owner_org","owner_org_unit","primary_contact","problem_reason","problem_source","problem_type","reopen_count","reopen_timestamp","resolution_complexity","resolution_cost","resolved_division","resolved_org","resolved_org_unit","resolved_time","resolved_time_stamp","resolved_user_id","resolved_user_workgroup","root_cause","sla_deadline","sla_goal","sla_name","status_customer_sat","status_work","work_list_date1","work_list_datetime1","work_list_datetime2","work_list_decimal1","work_list_decimal2","work_list_integer1","work_list_text1","work_list_text2","work_list_text3","ins_key","pv_stream","fpc_case_id","fi_case_status","fpc_created_at_sap","time_of_status_changed","sap_validation_errmess","status_work_timestamp","initiative_name","imdo","initiative_id","request_type","sap_snapshot_id","sap_completion_date","sap_trigger_date","retry_count_forbom","exception_imdo_comments","exception_approver_comments","exception_outcome","sap_connection_status","dynamic_data","source_pega","case_type","sap_idoc_request_xml","is_in_sap","title","active_in_dsbp","market_extension_initiative_only","text","action_requested","retry_count","assigned_to_manager","tmp_title_sap","pv_sla_name","stage_status","note","fpc_id","commit_date_time","create_date_time","create_op_name","create_operator","create_system_id","ins_name","obj_class","save_date_time","update_date_time","update_op_name","update_operator","update_system_id","label","ins_key","ref_ins_name","pv_stream","initiative_id","initiative_name","imdo","iol","pi_fpc_id","template_description","template_id","rule_set_name","final_fpc_number","fi_case_status","ref_nref_1","il","text_initiative_id","rejection_reason","rejection_notes","dr_financial_flag","request_type","dynamic_data","source_pega","case_type","illon_name","cdp_flag","cs_predefined_gtin","it_predefined_gtin","sw_predefined_gtin","primary_case_id","fater_fpc","special_gtin","is_in_sap","program_reference_number","forecast_submission"
                        22469,,,2025-11-11 16:24:10.035,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110392,,2025-11-11 16:24:10.035,2025-11-11 16:24:10.035,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110392,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:10.000,2026-05-10 16:24:10.000,,,Open-FailedValidation,,,,,,,,,,CF-110392,,CF-110392,,,,,2025-11-26 15:46:35.339,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37293,2025-11-11 16:24:19.887,2025-11-11 16:24:10.038,SYSTEM,SYSTEM,MADS_QA,CF-110392,,2025-11-11 16:24:19.887,2025-11-11 16:24:10.038,,,,,CF-110392,CF-110392,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423850,Male Premium Blade,BLAPREMMA,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        22464,,,2025-11-11 16:24:09.742,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110387,,2025-11-11 16:24:09.742,2025-11-11 16:24:09.742,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110387,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:09.000,2026-05-10 16:24:09.000,,,Open-FailedValidation,,,,,,,,,,CF-110387,,CF-110387,,,,,2025-11-26 15:46:34.371,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37288,2025-11-11 16:24:19.969,2025-11-11 16:24:09.757,SYSTEM,SYSTEM,MADS_QA,CF-110387,,2025-11-11 16:24:19.969,2025-11-11 16:24:09.757,,,,,CF-110387,CF-110387,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423847,Male Premium Blade,BLAPREMMA,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        22465,,,2025-11-11 16:24:09.848,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110388,,2025-11-11 16:24:09.848,2025-11-11 16:24:09.848,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110388,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:09.000,2026-05-10 16:24:09.000,,,Open-FailedValidation,,,,,,,,,,CF-110388,,CF-110388,,,,,2025-11-26 15:46:34.599,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37289,2025-11-11 16:24:20.101,2025-11-11 16:24:09.851,SYSTEM,SYSTEM,MADS_QA,CF-110388,,2025-11-11 16:24:20.101,2025-11-11 16:24:09.851,,,,,CF-110388,CF-110388,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423851,Male Premium Blade,BLAPREMMA,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        22467,,,2025-11-11 16:24:09.936,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110390,,2025-11-11 16:24:09.936,2025-11-11 16:24:09.936,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110390,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:09.000,2026-05-10 16:24:09.000,,,Open-FailedValidation,,,,,,,,,,CF-110390,,CF-110390,,,,,2025-11-26 15:46:36.026,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37291,2025-11-11 16:24:22.141,2025-11-11 16:24:09.939,SYSTEM,SYSTEM,MADS_QA,CF-110390,,2025-11-11 16:24:22.141,2025-11-11 16:24:09.939,,,,,CF-110390,CF-110390,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423849,Male Shave Prep,SHVPREPMAL,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        22466,,,2025-11-11 16:24:09.893,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110389,,2025-11-11 16:24:09.893,2025-11-11 16:24:09.893,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110389,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:09.000,2026-05-10 16:24:09.000,,,Open-FailedValidation,,,,,,,,,,CF-110389,,CF-110389,,,,,2025-11-26 15:46:34.141,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37290,2025-11-11 16:24:22.430,2025-11-11 16:24:09.897,SYSTEM,SYSTEM,MADS_QA,CF-110389,,2025-11-11 16:24:22.430,2025-11-11 16:24:09.897,,,,,CF-110389,CF-110389,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423848,Male Shave Prep,SHVPREPMAL,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        22468,,,2025-11-11 16:24:09.989,,,,,,SYSTEM,SYSTEM,MADS_QA,,,,,CF-110391,,2025-11-11 16:24:09.989,2025-11-11 16:24:09.989,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,CF-110391,Create FPC,,,,,System,,,,,,,,,,,,,,,,,,,,,2026-05-11 16:24:09.000,2026-05-10 16:24:09.000,,,Open-FailedValidation,,,,,,,,,,CF-110391,,CF-110391,,,,,2025-11-26 15:46:36.726,PEGA Exit 2,salazar.jd.2,"10213352",Create,,,,,,,,,"",,,,,,,,,,0,,,,,,37292,2025-11-11 16:24:22.771,2025-11-11 16:24:09.993,SYSTEM,SYSTEM,MADS_QA,CF-110391,,2025-11-11 16:24:22.771,2025-11-11 16:24:09.993,,,,,CF-110391,CF-110391,,"10213352",PEGA Exit 2,salazar.jd.2,salazar.jd.2,FPC423852,Male Shave Prep,SHVPREPMAL,,"",,,,"10213352",,,,Create,"",false,,salazar.jd.2,N,,,,,,,,,
                        """;
                return "Users data: " + response;
            } else if (lowerQuestion.contains("post") || lowerQuestion.contains("article") || lowerQuestion.contains("blog")) {
                String response = restTemplate.getForObject(baseUrl + "/posts?_limit=5", String.class);
                return "Posts data: " + response;
            } else if (lowerQuestion.contains("comment")) {
                String response = restTemplate.getForObject(baseUrl + "/comments?_limit=5", String.class);
                return "Comments data: " + response;
            } else if (lowerQuestion.contains("todo") || lowerQuestion.contains("task")) {
                String response = restTemplate.getForObject(baseUrl + "/todos?_limit=5", String.class);
                return "Todos data: " + response;
            } else if (lowerQuestion.contains("album") || lowerQuestion.contains("photo")) {
                String response = restTemplate.getForObject(baseUrl + "/albums?_limit=5", String.class);
                return "Albums data: " + response;
            } else {
                String response = restTemplate.getForObject(baseUrl + "/posts?_limit=3", String.class);
                return "General data (posts): " + response;
            }
        } catch (Exception e) {
            return "Error fetching data: " + e.getMessage();
        }
    }
}
