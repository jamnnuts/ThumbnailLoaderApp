import '/components/consultation_header_widget.dart';
import '/components/side_menu_stages_widget.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'e_c_g_test_widget.dart' show ECGTestWidget;
import 'package:flutter/material.dart';

class ECGTestModel extends FlutterFlowModel<ECGTestWidget> {
  ///  State fields for stateful widgets in this page.

  final unfocusNode = FocusNode();
  // Model for ConsultationHeader component.
  late ConsultationHeaderModel consultationHeaderModel;
  // Model for SideMenuStages component.
  late SideMenuStagesModel sideMenuStagesModel;

  /// Initialization and disposal methods.

  @override
  void initState(BuildContext context) {
    consultationHeaderModel =
        createModel(context, () => ConsultationHeaderModel());
    sideMenuStagesModel = createModel(context, () => SideMenuStagesModel());
  }

  @override
  void dispose() {
    unfocusNode.dispose();
    consultationHeaderModel.dispose();
    sideMenuStagesModel.dispose();
  }

  /// Action blocks are added here.

  /// Additional helper methods are added here.
}
