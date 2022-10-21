package com.crm.sofia.services.form;

import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FormCacheingService {

    private Map<Long, FormUiDTO> cachedForms = new HashMap<>();

    public FormUiDTO getUiObject(Long id) {
        if (this.cachedForms.containsKey(id)) {
            return this.cachedForms.get(id);
        } else {
            return null;
        }
    }

    public Boolean hasUiObject(Long id) {
        if (this.cachedForms.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public void putUiObject(Long id, FormUiDTO formUiDTO) {
        this.cachedForms.put(id, formUiDTO);
    }

    public void clear() {
        this.cachedForms.clear();
    }

    public void clearUiObject(Long id) {
        if (this.cachedForms.containsKey(id)) {
            this.cachedForms.remove(id);
        }
    }

//    public boolean hasFormUiObjectWithVersion(Long id, String versionId) {
//        if (this.cachedForms.containsKey(id)) {
//            FormUiDTO formUiDTO = this.cachedForms.get(id);
//            if (versionId.equals(formUiDTO.getVersionId())) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

}
