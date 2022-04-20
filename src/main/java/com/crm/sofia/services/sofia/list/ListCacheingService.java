package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ListCacheingService {

    private Map<Long, ListUiDTO> cachedLists = new HashMap<>();

    public ListUiDTO getUiObject(String id) {
        if (this.cachedLists.containsKey(id)) {
            return this.cachedLists.get(id);
        } else {
            return null;
        }
    }

    public Boolean hasUiObject(String id) {
        if (this.cachedLists.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public void putUiObject(Long id, ListUiDTO listUiDTO) {
        this.cachedLists.put(id, listUiDTO);
    }

    public void clearUiObject(String id) {
        if (this.cachedLists.containsKey(id)) {
            this.cachedLists.remove(id);
        }
    }

    public void clear() {
            this.cachedLists.clear();
    }
}
