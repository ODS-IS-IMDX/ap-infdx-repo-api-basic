// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import com.spatialid.app.common.validation.Groups.First;
import com.spatialid.app.common.validation.Groups.Forth;
import com.spatialid.app.common.validation.Groups.Second;
import com.spatialid.app.common.validation.Groups.Third;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({ Default.class, First.class, Second.class, Third.class, Forth.class })
public interface Sequence {

}
