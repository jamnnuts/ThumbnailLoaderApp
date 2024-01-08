import '/flutter_flow/flutter_flow_util.dart';
import 'new_patient_widget.dart' show NewPatientWidget;
import 'package:flutter/material.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';

class NewPatientModel extends FlutterFlowModel<NewPatientWidget> {
  ///  State fields for stateful widgets in this page.

  final unfocusNode = FocusNode();
  final formKey = GlobalKey<FormState>();
  // State field(s) for First widget.
  FocusNode? firstFocusNode;
  TextEditingController? firstController;
  String? Function(BuildContext, String?)? firstControllerValidator;
  // State field(s) for Last widget.
  FocusNode? lastFocusNode;
  TextEditingController? lastController;
  String? Function(BuildContext, String?)? lastControllerValidator;
  // State field(s) for Alias widget.
  FocusNode? aliasFocusNode;
  TextEditingController? aliasController;
  String? Function(BuildContext, String?)? aliasControllerValidator;
  // State field(s) for Phone widget.
  FocusNode? phoneFocusNode;
  TextEditingController? phoneController;
  String? Function(BuildContext, String?)? phoneControllerValidator;
  // State field(s) for DOB widget.
  FocusNode? dobFocusNode;
  TextEditingController? dobController;
  final dobMask = MaskTextInputFormatter(mask: '##/##/####');
  String? Function(BuildContext, String?)? dobControllerValidator;
  // State field(s) for DOBAlias widget.
  FocusNode? dOBAliasFocusNode;
  TextEditingController? dOBAliasController;
  final dOBAliasMask = MaskTextInputFormatter(mask: '##/##/####');
  String? Function(BuildContext, String?)? dOBAliasControllerValidator;

  /// Initialization and disposal methods.

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {
    unfocusNode.dispose();
    firstFocusNode?.dispose();
    firstController?.dispose();

    lastFocusNode?.dispose();
    lastController?.dispose();

    aliasFocusNode?.dispose();
    aliasController?.dispose();

    phoneFocusNode?.dispose();
    phoneController?.dispose();

    dobFocusNode?.dispose();
    dobController?.dispose();

    dOBAliasFocusNode?.dispose();
    dOBAliasController?.dispose();
  }

  /// Action blocks are added here.

  /// Additional helper methods are added here.
}
